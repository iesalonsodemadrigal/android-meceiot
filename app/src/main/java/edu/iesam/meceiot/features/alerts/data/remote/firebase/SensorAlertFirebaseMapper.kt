package edu.iesam.meceiot.features.alerts.data.remote.firebase

import edu.iesam.meceiot.features.alerts.domain.Panel
import edu.iesam.meceiot.features.alerts.domain.Sensor
import edu.iesam.meceiot.features.alerts.domain.TypeSensor


fun PanelAlertFirebaseModel.toDomain(): Panel {
    val sensors = sensors.values.map { it.toDomain() }
    return Panel(this.id, this.name, sensors)
}

fun SensorAlertFirebaseModel.toDomain(): Sensor {
    return Sensor(this.id, this.name, TypeSensor.fromType(type), this.value)
}