package edu.iesam.meceiot.features.alerts.domain

data class Panels(
    val panels: Map<String, Sensor>
)

data class Sensor(
    val id: String,
    val name: String,
    val description: String,
    val movement: Int,
    //add more detections
)