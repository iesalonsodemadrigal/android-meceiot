package edu.iesam.meceiot.features.alerts.domain


import org.koin.core.annotation.Single

@Single
class GetSensorUseCase(private val sensorRepository: SensorRepository) {

    suspend operator fun invoke(): Result<List<Sensor>> {
        val result = sensorRepository.getSensors()

        val filteredSensor = result.getOrNull()?.filter { sensor ->
            sensor.type == TypeSensor.Co2 && sensor.value < "200" ||
                    sensor.type == TypeSensor.Temperature && sensor.value <= "15" ||
                    sensor.type == TypeSensor.Light && sensor.value >= "500" ||
                    sensor.type == TypeSensor.Humidity && sensor.value == "32" ||
                    sensor.type == TypeSensor.Movement && sensor.value != "0" && sensor.value >= "1" ||
                    sensor.type == TypeSensor.Sound && sensor.value >= "40"

        } ?: emptyList()

        return Result.success(filteredSensor)
    }
}