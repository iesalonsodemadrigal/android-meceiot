package edu.iesam.meceiot.features.alerts.data.remote

import edu.iesam.meceiot.core.data.remote.apiCall
import edu.iesam.meceiot.features.alerts.domain.Panel
import org.koin.core.annotation.Single

@Single
class SensorApiRemoteDataSource(private val sensorService: SensorService) {

    suspend fun getSensors(): Result<List<Panel>> {
        return apiCall { sensorService.getSensors() }.map { sensorApiModel ->
            sensorApiModel.map { it.toModel() }
        }
    }
}