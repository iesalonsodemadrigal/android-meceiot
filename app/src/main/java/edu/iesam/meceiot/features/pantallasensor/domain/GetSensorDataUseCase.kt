package edu.iesam.meceiot.features.pantallasensor.domain

import org.koin.core.annotation.Single

@Single
class GetSensorDataUseCase(private val sensorRepository: SensorRepository) {
    suspend operator fun invoke(id: Int): Sensor {
        return sensorRepository.getSensorDataById(id)
    }
}