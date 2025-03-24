package edu.iesam.meceiot.features.pantallasensor.domain

interface GraphSensorRepository {
    suspend fun getSensorDataById(id: Int, query: String): Result<GraphSensor>
}