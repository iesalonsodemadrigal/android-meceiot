package edu.iesam.meceiot.features.alerts.domain

interface SensorRepository {

    fun getSensors(): Result<List<Sensor>>
}