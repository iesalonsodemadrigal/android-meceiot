package edu.iesam.meceiot.features.pantallasensor.data

import edu.iesam.meceiot.features.pantallasensor.data.local.db.SensorDbDataSource
import edu.iesam.meceiot.features.pantallasensor.data.remote.SensorRemoteMockDataSource
import edu.iesam.meceiot.features.pantallasensor.domain.Sensor
import edu.iesam.meceiot.features.pantallasensor.domain.SensorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Single

@Single
class SensorDataRepository(
    private val remoteDataSource: SensorRemoteMockDataSource,
    private val localDataSource: SensorDbDataSource
) : SensorRepository {
    override suspend fun getSensorDataById(id: Int): Sensor = withContext(Dispatchers.IO) {
        val localSensor = localDataSource.getById(id)
        if (localSensor == null) {
            val remoteSensor = remoteDataSource.getSensorData()
            localDataSource.save(remoteSensor)
            remoteSensor
        } else {
            localSensor
        }
    }
}