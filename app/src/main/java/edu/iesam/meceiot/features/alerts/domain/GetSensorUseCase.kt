package edu.iesam.meceiot.features.alerts.domain

import android.util.Log
import org.koin.core.annotation.Single

@Single
class GetSensorUseCase(private val sensorRepository: SensorRepository) {

    operator fun invoke(): Result<List<Sensor>> {
        val result = sensorRepository.getSensors()

        val sensor = result.getOrNull() ?: emptyList()
        val filteredSensor = sensor.filter { it.movement != 0 && it.movement > 0 }
        return Result.success(filteredSensor)
    }
}