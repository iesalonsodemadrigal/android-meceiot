package edu.iesam.meceiot.features.alerts.domain


import org.koin.core.annotation.Single

@Single
class GetSensorUseCase(private val sensorAlertRepository: SensorAlertRepository) {

    suspend operator fun invoke(): Result<List<Sensor>> {
        val result = sensorAlertRepository.getSensors()

        val filteredSensor = result.getOrNull()?.filter { sensor ->
            when (sensor.type) {
                TypeSensor.Co2 -> sensor.value.toDouble().let { it > 1800 }
                TypeSensor.Humidity -> sensor.value.toDouble().let { it <= 30 || it >= 60 }
                TypeSensor.Light -> sensor.value.toDouble().let { it > 1000 }
                TypeSensor.Movement -> sensor.value.toDouble().let { it >= 1 }
                TypeSensor.Sound -> sensor.value.toDouble().let { it >= 60 }
                TypeSensor.Temperature -> sensor.value.toDouble().let { it <= 18 || it >= 30 }
                TypeSensor.UnknownSensor -> false
            }
        } ?: emptyList()

        return Result.success(filteredSensor)
    }
}