package edu.iesam.meceiot.features.pantallasensor.domain

interface GraphSensorRepository {
    suspend fun getSensorDataById(id: Int): Result<GraphSensor>
}