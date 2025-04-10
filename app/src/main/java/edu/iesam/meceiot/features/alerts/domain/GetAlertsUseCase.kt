package edu.iesam.meceiot.features.alerts.domain


import org.koin.core.annotation.Single

@Single
class GetAlertsUseCase(private val sensorRepository: AlertRepository) {

    suspend operator fun invoke(): Result<List<Alert>> {
        val result = sensorRepository.getAlerts()

        return result.fold(
            onSuccess = { alerts ->
                val filteredSensor = alerts.filter { sensor ->
                    when (sensor.type) {
                        TypeSensor.Co2 -> sensor.value.toIntOrNull()?.let { it >= 800 } == true
                        TypeSensor.Temperature -> sensor.value.toDoubleOrNull()
                            ?.let { it <= 19 || it >= 27 } == true

                        TypeSensor.Light -> sensor.value.toIntOrNull()
                            ?.let { it <= 300 || it >= 700 } == true

                        TypeSensor.Humidity -> sensor.value.toDoubleOrNull()
                            ?.let { it <= 40 || it >= 60 } == true

                        TypeSensor.Movement -> sensor.value.toIntOrNull()
                            ?.let { it != 0 && it >= 1 } == true

                        TypeSensor.Sound -> sensor.value.toIntOrNull()?.let { it >= 65 } == true
                        else -> false
                    }
                }
                Result.success(filteredSensor)
            },
            onFailure = { error ->
                Result.failure(error)
            }
        )
        /*val filteredSensor = result.getOrNull()?.filter { sensor ->
            when (sensor.type) {
                TypeSensor.Co2 -> sensor.value.toIntOrNull()?.let { it >= 800 } ?: false
                TypeSensor.Temperature -> sensor.value.toDoubleOrNull()
                    ?.let { it <= 19 || it >= 27 } ?: false

                TypeSensor.Light -> sensor.value.toIntOrNull()?.let { it <= 300 || it >= 700 }
                    ?: false

                TypeSensor.Humidity -> sensor.value.toDoubleOrNull()?.let { it <= 40 || it >= 60 }
                    ?: false

                TypeSensor.Movement -> sensor.value.toIntOrNull()?.let { it != 0 && it >= 1 }
                    ?: false

                TypeSensor.Sound -> sensor.value.toIntOrNull()?.let { it >= 65 } ?: false
                else -> false
            }
        }
        return if (filteredSensor != null) {
            Result.success(filteredSensor)
        } else {
            Result.failure(it)
        }*/
    }
}