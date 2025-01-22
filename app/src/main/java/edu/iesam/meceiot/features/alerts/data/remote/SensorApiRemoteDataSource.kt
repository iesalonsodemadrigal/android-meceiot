package edu.iesam.meceiot.features.alerts.data.remote

import edu.iesam.meceiot.core.data.remote.apiCall
import edu.iesam.meceiot.features.alerts.domain.Panel
import org.koin.core.annotation.Single

@Single
class SensorApiRemoteDataSource(private val sensorAlertService: SensorAlertService) {

    suspend fun getSensors(): Result<List<Panel>> {
        return apiCall { sensorAlertService.getSensors() }.map { sensorApiModel ->
            sensorApiModel.map { it.toModel() }
        }
    }
}