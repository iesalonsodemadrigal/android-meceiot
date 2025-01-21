package edu.iesam.meceiot.features.sensorpanels.data.local.db

import edu.iesam.meceiot.features.sensorpanels.domain.Panel
import edu.iesam.meceiot.features.sensorpanels.domain.Sensor
import java.util.Date

fun Sensor.toEntity(): SensorEntity {
    return SensorEntity(
        id = id,
        name = name
    )
}

fun SensorEntity.toDomain(): Sensor {
    return Sensor(
        id = id,
        name = name
    )
}

fun Panel.toEntity(date: Date): PanelEntity {
    return PanelEntity(
        id = id,
        name = name,
        sensors = sensors,
        date = date
    )
}

fun PanelEntity.toDomain(): Panel {
    return Panel(
        id = id,
        name = name,
        sensors = sensors
    )
}
