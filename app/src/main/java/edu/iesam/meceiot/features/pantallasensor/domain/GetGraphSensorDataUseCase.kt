package edu.iesam.meceiot.features.pantallasensor.domain

import org.koin.core.annotation.Single

@Single
class GetGraphSensorDataUseCase(private val graphSensorRepository: GraphSensorRepository) {
    suspend operator fun invoke(id: Int, query: String, from: Long, to: Long): Result<GraphSensor> {
        return graphSensorRepository.getSensorDataById(id, query, from, to)
    }
}