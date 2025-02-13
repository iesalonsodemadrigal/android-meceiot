package edu.iesam.meceiot.features.sensorpanels.domain

interface SensorPanelRepository {
    suspend fun getSensorPanels(): Result<List<Panel>>
}