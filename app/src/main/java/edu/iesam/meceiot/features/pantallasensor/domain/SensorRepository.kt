package edu.iesam.meceiot.features.pantallasensor.domain

interface SensorRepository {
    fun getSensorData(): List<Sensor>
}