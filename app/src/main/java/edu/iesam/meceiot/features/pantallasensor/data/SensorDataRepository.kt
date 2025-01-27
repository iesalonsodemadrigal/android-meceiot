package edu.iesam.meceiot.features.pantallasensor.data

import edu.iesam.meceiot.features.pantallasensor.data.local.db.SensorDbDataSource
import edu.iesam.meceiot.features.pantallasensor.data.remote.SensorRemoteMockDataSource
import edu.iesam.meceiot.features.pantallasensor.domain.Sensor
import edu.iesam.meceiot.features.pantallasensor.domain.SensorRepository
import org.koin.core.annotation.Single

@Single
class SensorDataRepository(
    private val remoteDataSource: SensorRemoteMockDataSource,
    private val localDataSource: SensorDbDataSource
) : SensorRepository {
    override suspend fun getSensorDataById(id: Int): Result<Sensor> {
        val localResult = localDataSource.getById(id)
        return if (localResult.isSuccess) {
            localResult
        } else {
            val remoteResult = remoteDataSource.getSensorData()
            if (remoteResult.isSuccess) {
                localDataSource.save(remoteResult.getOrThrow())
            }
            remoteResult
        }
    }
}