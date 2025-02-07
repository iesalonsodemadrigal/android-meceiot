package edu.iesam.meceiot.features.pantallasensor.data.remote

import edu.iesam.meceiot.core.data.remote.apiCall
import edu.iesam.meceiot.features.pantallasensor.domain.GraphSensor
import org.koin.core.annotation.Single

@Single
class GraphSensorRetrofitRemoteDataSource(private val graphSensorService: GraphSensorService) {
    suspend fun getSensorData(): Result<GraphSensor> {
        return apiCall { graphSensorService.getSensorData() }.map { it.toModel() }
    }
}