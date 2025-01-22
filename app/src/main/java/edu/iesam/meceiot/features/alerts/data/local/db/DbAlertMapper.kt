package edu.iesam.meceiot.features.alerts.data.local.db

import edu.iesam.meceiot.features.alerts.domain.Panel
import edu.iesam.meceiot.features.alerts.domain.Sensor

fun Panel.toEntity(): PanelAlertEntity =
    PanelAlertEntity(this.id, this.name, this.sensors)

fun PanelAlertEntity.toDomain(): Panel =
    Panel(this.id, this.name, this.sensors)

fun Sensor.toEntity(): SensorAlertEntity =
    SensorAlertEntity(this.id, this.name, this.description, this.type, this.value)

fun SensorAlertEntity.toDomain(): Sensor =
    Sensor(this.id, this.name, this.description, this.type, this.value)