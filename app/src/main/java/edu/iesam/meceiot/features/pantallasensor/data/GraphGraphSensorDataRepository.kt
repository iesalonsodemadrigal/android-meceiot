package edu.iesam.meceiot.features.pantallasensor.data

import edu.iesam.meceiot.features.grafana.data.remote.GrafanaRemoteDataSource
import edu.iesam.meceiot.features.pantallasensor.data.local.db.SensorDbDataSource
import edu.iesam.meceiot.features.pantallasensor.domain.GraphSensor
import edu.iesam.meceiot.features.pantallasensor.domain.GraphSensorRepository
import org.koin.core.annotation.Single

@Single
class GraphGraphSensorDataRepository(
    private val remoteDataSource: GrafanaRemoteDataSource,
    private val localDataSource: SensorDbDataSource
) : GraphSensorRepository {
    override suspend fun getSensorDataById(id: Int, query: String): Result<GraphSensor> {
        val localResult = localDataSource.getById(id)
        return if (localResult.isSuccess) {
            localResult
        } else {
            val remoteResult = remoteDataSource.getSensorData(query)
            if (remoteResult.isSuccess) {
                localDataSource.save(remoteResult.getOrThrow())
            }
            remoteResult
        }
    }
}