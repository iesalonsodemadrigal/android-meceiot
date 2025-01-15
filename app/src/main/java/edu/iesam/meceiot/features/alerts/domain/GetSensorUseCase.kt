package edu.iesam.meceiot.features.alerts.domain


import org.koin.core.annotation.Single

@Single
class GetSensorUseCase(private val sensorRepository: SensorRepository) {

    operator fun invoke(): Result<List<Zone>> {
        val result = sensorRepository.getSensors()

        val zones = result.getOrNull() ?: emptyList()

        val filteredSensors = zones.filter { zone ->
            zone.sensors.any { sensor ->
                sensor.type == "mov" && sensor.value != "0" && sensor.value >= "1"
            }
        }

        return Result.success(filteredSensors)
    }
}