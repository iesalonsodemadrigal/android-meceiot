package edu.iesam.meceiot.features.alerts.data.remote

import edu.iesam.meceiot.features.alerts.domain.Panel
import edu.iesam.meceiot.features.alerts.domain.Sensor

fun PanelApiModel.toModel(): Panel {
    val sensorList = sensors.map { it.toModel() }
    return Panel(idZone, nameZone, sensorList)
}

fun SensorApiModel.toModel(): Sensor {
    val typeSensor = type.toTypeSensor()
    return Sensor(idSensor, nameSensor, typeSensor, value)
}