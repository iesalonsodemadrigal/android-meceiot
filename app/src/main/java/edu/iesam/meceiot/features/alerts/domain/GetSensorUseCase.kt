package edu.iesam.meceiot.features.alerts.domain


import org.koin.core.annotation.Single

@Single
class GetSensorUseCase(private val sensorRepository: SensorRepository) {

    operator fun invoke(): Result<List<Zone>> {
        val result = sensorRepository.getSensors()

        val filteredSensor = result.getOrNull()?.map { zone ->
            zone.copy(
                sensors = zone.sensors.filter { sensor ->
                    sensor.type == "mov" && sensor.value != "0" && sensor.value >= "1"
                }
            )
        }?.filter { zone ->
            zone.sensors.isNotEmpty()
        } ?: emptyList()



        return Result.success(filteredSensor)
    }
}