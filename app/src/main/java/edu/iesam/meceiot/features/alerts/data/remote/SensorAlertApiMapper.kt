package edu.iesam.meceiot.features.alerts.data.remote

import edu.iesam.meceiot.features.alerts.domain.Alert
import edu.iesam.meceiot.features.alerts.domain.Panel

fun PanelApiModel.toModel(): Panel {
    val sensorList = sensors.map { it.toModel() }
    return Panel(idZone, nameZone, sensorList)
}

fun SensorApiModel.toModel(): Alert {
    val typeSensor = type.toTypeSensor()
    return Alert(idSensor, nameSensor, typeSensor, value, description)
}