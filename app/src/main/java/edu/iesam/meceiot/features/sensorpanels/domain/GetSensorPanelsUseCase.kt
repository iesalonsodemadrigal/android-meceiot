package edu.iesam.meceiot.features.sensorpanels.domain

import org.koin.core.annotation.Single

@Single
class GetSensorPanelsUseCase(private val repository: SensorPanelRepository) {
    suspend fun invoke(): Result<List<Panel>> {
        return repository.getSensorPanels()
    }
}