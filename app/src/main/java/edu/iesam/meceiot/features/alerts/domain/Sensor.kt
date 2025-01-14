package edu.iesam.meceiot.features.alerts.domain

data class Panels(
    val panels: Map<String, Sensor>
)

data class Sensor(
    val id: String,
    val name: String,
    val location: String,
    val movement: Int,
    val temperature: Double
    //add more detections
)

data class Alert(
    val sensorId: String,
    val message: String
)