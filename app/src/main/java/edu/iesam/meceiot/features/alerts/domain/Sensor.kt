package edu.iesam.meceiot.features.alerts.domain

data class Zone(
    val id: String,
    val name: String,
    val sensors: List<Sensor>
)


data class Sensor(
    val id: String,
    val name: String,
    val description: String,
    val type: String,
    val value: String
)