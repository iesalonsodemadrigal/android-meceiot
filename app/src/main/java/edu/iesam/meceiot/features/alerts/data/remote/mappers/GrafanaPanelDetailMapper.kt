package edu.iesam.meceiot.features.alerts.data.remote.mappers

import android.util.Log
import edu.iesam.meceiot.features.alerts.data.remote.grafanamodel.PanelDetailGrafanaModel
import edu.iesam.meceiot.features.alerts.domain.Panel
import edu.iesam.meceiot.features.alerts.domain.Sensor
import edu.iesam.meceiot.features.alerts.domain.TypeSensor


fun PanelDetailGrafanaModel.toDomain(): Panel {
    val sensors = dashboard.panels.flatMap { panel ->
        panel.targets.map { target ->
            Sensor(
                id = panel.id.toString(),
                name = target.refId,
                type = extractTypeFromRefId(target.refId),
                value = ""
            )
        }
    }
    val panel = Panel(id = uid, name = title, sensors = sensors)
    Log.d("@dev", "panel $panel")
    return panel
}


fun extractTypeFromRefId(refId: String): TypeSensor {
    return when {
        refId.contains("Sound", ignoreCase = true) -> TypeSensor.Sound
        refId.contains("Temp", ignoreCase = true) -> TypeSensor.Temperature
        refId.contains("Luz", ignoreCase = true) -> TypeSensor.Light
        refId.contains("Hum", ignoreCase = true) -> TypeSensor.Humidity
        refId.contains("CO2", ignoreCase = true) -> TypeSensor.Co2
        refId.contains("Movimiento", ignoreCase = true) -> TypeSensor.Movement
        else -> TypeSensor.UnknownSensor
    }
}
