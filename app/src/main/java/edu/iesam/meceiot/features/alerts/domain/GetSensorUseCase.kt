package edu.iesam.meceiot.features.alerts.domain

import android.util.Log
import org.koin.core.annotation.Single

@Single


class GetSensorUseCase(private val sensorRepository: SensorRepository) {

    operator fun invoke(): Result<List<Sensor>> {
        val result = sensorRepository.getSensors()

        val sensor = result.getOrNull() ?: emptyList()
        Log.d("@dev", "Sensores obtenidos: $sensor")
        val filteredSensor = sensor.filter {
            it.movement != 0 && it.movement > 0
            //it.temperature <= 10.0 || it.temperature >= 32.0
        }

        Log.d("@dev", "Sensores obtenidos: $filteredSensor")

        return Result.success(filteredSensor)
    }
}