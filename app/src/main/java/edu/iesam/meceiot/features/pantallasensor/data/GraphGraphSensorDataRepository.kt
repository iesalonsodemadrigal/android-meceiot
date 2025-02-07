package edu.iesam.meceiot.features.pantallasensor.data

import edu.iesam.meceiot.features.pantallasensor.data.local.db.SensorDbDataSource
import edu.iesam.meceiot.features.pantallasensor.data.remote.firebase.GraphSensorFirebaseRemoteDataSource
import edu.iesam.meceiot.features.pantallasensor.domain.GraphSensor
import edu.iesam.meceiot.features.pantallasensor.domain.GraphSensorRepository
import org.koin.core.annotation.Single

@Single
class GraphGraphSensorDataRepository(
    private val remoteDataSource: GraphSensorFirebaseRemoteDataSource,
    private val localDataSource: SensorDbDataSource
) : GraphSensorRepository {
    override suspend fun getSensorDataById(id: Int): Result<GraphSensor> {
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