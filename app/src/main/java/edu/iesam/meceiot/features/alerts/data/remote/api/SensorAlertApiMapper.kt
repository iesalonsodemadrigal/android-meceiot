package edu.iesam.meceiot.features.alerts.data.remote.api

import edu.iesam.meceiot.features.alerts.domain.Panel
import edu.iesam.meceiot.features.alerts.domain.Sensor

fun PanelApiModel.toModel(): Panel {
    val sensorList = sensors.map { it.toModel() }
    return Panel(id, name, sensorList)
}

fun SensorApiModel.toModel(): Sensor {
    val typeSensor = type.toTypeSensor()
    return Sensor(id, name, typeSensor, value)
}