package edu.iesam.meceiot.features.alerts.domain

interface SensorAlertRepository {

    suspend fun getSensors(): Result<List<Sensor>>
}