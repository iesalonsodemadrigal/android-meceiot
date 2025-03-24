package edu.iesam.meceiot.features.sensorpanels.data.local.db

import edu.iesam.meceiot.features.sensorpanels.domain.Panel
import edu.iesam.meceiot.features.sensorpanels.domain.Sensor

fun Sensor.toEntity(): SensorEntity {
    return SensorEntity(
        id = id,
        name = name,
        panelName = panelName,
        query = query
    )
}

fun SensorEntity.toDomain(): Sensor {
    return Sensor(
        id = id,
        name = name,
        panelName = panelName,
        query = query
    )
}

fun Panel.toEntity(date: Long): PanelEntity {
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
