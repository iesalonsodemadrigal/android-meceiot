package edu.iesam.meceiot.features.pantallasensor.data

import edu.iesam.meceiot.features.grafana.data.remote.GrafanaRemoteDataSource
import edu.iesam.meceiot.features.pantallasensor.data.local.db.SensorDbDataSource
import edu.iesam.meceiot.features.pantallasensor.domain.GraphSensor
import edu.iesam.meceiot.features.pantallasensor.domain.GraphSensorRepository
import org.koin.core.annotation.Single

@Single
class GraphGraphSensorDataRepository(
    private val remote: GrafanaRemoteDataSource,
    private val local: SensorDbDataSource
) : GraphSensorRepository {
    override suspend fun getSensorDataById(
        id: Int,
        query: String,
        from: Long,
        to: Long
    ): Result<GraphSensor> {
        val localResult = local.getByIdAndDateRange(id, from, to)
        return if (localResult.isFailure) {
            val remoteResult = remote.getSensorData(query, from, to)
            remoteResult.apply {
                onSuccess {
                    local.save(it, from, to)
                }
            }
        } else {
            return localResult
        }
    }
}