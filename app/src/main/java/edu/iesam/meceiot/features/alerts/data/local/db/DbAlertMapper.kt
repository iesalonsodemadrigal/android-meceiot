package edu.iesam.meceiot.features.alerts.data.local.db

import edu.iesam.meceiot.features.alerts.domain.Panel
import edu.iesam.meceiot.features.alerts.domain.Sensor

fun Panel.toEntity(): PanelEntity =
    PanelEntity(this.id, this.name, this.sensors)

fun PanelEntity.toDomain(): Panel =
    Panel(this.id, this.name, this.sensors)

fun Sensor.toEntity(): SensorEntity =
    SensorEntity(this.id, this.name, this.description, this.type, this.value)

fun SensorEntity.toDomain(): Sensor =
    Sensor(this.id, this.name, this.description, this.type, this.value)