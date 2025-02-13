package edu.iesam.meceiot.features.sensorpanels.data.remote

import edu.iesam.meceiot.features.sensorpanels.domain.Panel
import edu.iesam.meceiot.features.sensorpanels.domain.Sensor


fun PanelApiModel.toDomain(): Panel {
    return Panel(
        id = id,
        name = name,
        sensors = sensors.map { it.toDomain() }
    )
}

fun SensorApiModel.toDomain(): Sensor {
    return Sensor(
        id = id,
        name = name
    )
}