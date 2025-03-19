package edu.iesam.meceiot.features.sensorpanels.domain

data class Sensor(
    val id: Int,
    val name: String,
    val panelName: String,
    val query: String
)