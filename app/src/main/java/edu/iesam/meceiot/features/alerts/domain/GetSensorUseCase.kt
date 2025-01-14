package edu.iesam.meceiot.features.alerts.domain

import org.koin.core.annotation.Single

@Single
class GetSensorUseCase(private val sensorRepository: SensorRepository) {

    operator fun invoke(): Result<List<Sensor>> {
        return sensorRepository.getSensors()
    }
}