package edu.iesam.meceiot.features.pantallasensor.domain

import org.koin.core.annotation.Single

@Single
class GetGraphSensorDataUseCase(private val graphSensorRepository: GraphSensorRepository) {
    suspend operator fun invoke(id: Int): Result<GraphSensor> {
        return graphSensorRepository.getSensorDataById(id)
    }
}