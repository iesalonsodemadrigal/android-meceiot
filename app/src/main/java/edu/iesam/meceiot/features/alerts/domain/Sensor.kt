package edu.iesam.meceiot.features.alerts.domain

data class Sensor(
    val id: String,
    val name: String,
    val location: String,
    val movement: Boolean
    //add more detections
)