package edu.iesam.meceiot.features.sensorpanels.data.remote

import edu.iesam.meceiot.core.data.remote.apiCall
import edu.iesam.meceiot.features.sensorpanels.domain.Panel
import org.koin.core.annotation.Single

@Single
class SensorPanelRemoteDataSource(
    private val apiClient: SensorPanelsService
) {
    suspend fun getSensorPanels(): Result<List<Panel>> {
        return apiCall {
            apiClient.getSensorPanels()
        }.map { it.map { it.toDomain() } }
    }
}