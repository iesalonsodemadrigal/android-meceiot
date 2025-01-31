package edu.iesam.meceiot.features.alerts.data.remote

import edu.iesam.meceiot.core.data.remote.apiCall
import edu.iesam.meceiot.features.alerts.domain.Sensor
import org.koin.core.annotation.Single

@Single
class SensorApiRemoteDataSource(private val sensorService: SensorService) {

    suspend fun getSensors(): Result<List<Sensor>> {
        return apiCall { sensorService.getSensors() }.map { sensorApiModel ->
            sensorApiModel.map { it.toModel() }
        }
    }
}