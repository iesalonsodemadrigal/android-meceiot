package edu.iesam.meceiot.features.sensorpanels.domain

data class Panel(
    val id: Int,
    val name: String,
    val sensors: List<Sensor>
)
