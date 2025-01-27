package edu.iesam.meceiot.features.pantallasensor.domain

interface SensorRepository {
    suspend fun getSensorDataById(id: Int): Result<Sensor>
}