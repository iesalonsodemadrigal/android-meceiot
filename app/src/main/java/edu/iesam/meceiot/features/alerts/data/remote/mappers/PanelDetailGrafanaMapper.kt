package edu.iesam.meceiot.features.alerts.data.remote.mappers

import edu.iesam.meceiot.features.alerts.data.remote.models.PanelDetailGrafanaModel
import edu.iesam.meceiot.features.alerts.domain.Panel
import edu.iesam.meceiot.features.alerts.domain.Sensor
import edu.iesam.meceiot.features.alerts.domain.TypeSensor


fun PanelDetailGrafanaModel.toDomain(): Panel {
    val sensors = dashboard.panels.flatMap { panel ->
        panel.targets.map { target ->
            Sensor(
                id = target.refId,
                name = title,
                type = extractTypeFromRefId(target.refId),
                value = ""
            )

        }
    }
    return Panel(id = uid, name = title, sensors = sensors)
}


fun extractTypeFromRefId(refId: String): TypeSensor {
    return when {
        refId.contains("Sound", ignoreCase = true) -> TypeSensor.Sound
        refId.contains("Temp [ºC]", ignoreCase = true) -> TypeSensor.Temperature
        refId.contains("Luz [lux]", ignoreCase = true) -> TypeSensor.Light
        refId.contains("Hum [%]", ignoreCase = true) -> TypeSensor.Humidity
        refId.contains("CO2 [ppm]", ignoreCase = true) -> TypeSensor.Co2
        refId.contains("Movimiento", ignoreCase = true) -> TypeSensor.Movement
        else -> TypeSensor.UnknownSensor
    }
}