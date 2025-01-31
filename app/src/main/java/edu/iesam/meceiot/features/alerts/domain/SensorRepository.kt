package edu.iesam.meceiot.features.alerts.domain

interface SensorRepository {

    suspend fun getSensors(): Result<List<Sensor>>
}