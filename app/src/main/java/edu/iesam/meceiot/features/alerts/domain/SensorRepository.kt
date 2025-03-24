package edu.iesam.meceiot.features.alerts.domain

interface SensorRepository {

    suspend fun getSensorAlerts(): Result<List<Sensor>>
}