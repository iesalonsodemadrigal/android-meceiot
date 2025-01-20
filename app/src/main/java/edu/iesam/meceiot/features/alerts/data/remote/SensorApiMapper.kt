package edu.iesam.meceiot.features.alerts.data.remote

import edu.iesam.meceiot.features.alerts.domain.Sensor
import edu.iesam.meceiot.features.alerts.domain.Zone

fun ZoneApiModel.toModel(): Zone {
    val sensorList = sensors.map { it.toModel() }
    return Zone(idZone, nameZone, sensorList)
}

fun SensorApiModel.toModel(): Sensor {
    val typeSensor = type.toTypeSensor() // Convertimos el tipo desde TypeSensorApiModel
    return Sensor(idSensor, nameSensor, description, typeSensor, value)
}