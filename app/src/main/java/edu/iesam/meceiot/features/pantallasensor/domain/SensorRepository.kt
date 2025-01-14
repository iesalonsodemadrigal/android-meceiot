package edu.iesam.meceiot.features.pantallasensor.domain

interface SensorRepository {
    fun getSensorDataById(id: Int): Sensor
}