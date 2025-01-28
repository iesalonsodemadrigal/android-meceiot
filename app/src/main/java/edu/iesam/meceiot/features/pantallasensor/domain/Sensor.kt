package edu.iesam.meceiot.features.pantallasensor.domain

data class Sensor(
    val id: Int,
    val name: String,
    val panelName: String,
    val dataType : String,
    val xValues: List<Long>,
    val yValues: List<Int>,
    val maxValue: String,
    val minValue: String,
    val avgValue: String,
    val modeValue: String
)