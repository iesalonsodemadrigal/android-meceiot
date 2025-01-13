package edu.iesam.meceiot.features.pantallasensor.domain

data class Sensor(
    val id: Int,
    val nombre: String,
    val nombrePanel: String,
    val valoresX: List<Long>,
    val valoresY: List<Int>,
    val leyendaX: String,
    val leyendaY: String
)