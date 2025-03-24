package edu.iesam.meceiot.features.sensorpanels.presentation.ui

import edu.iesam.meceiot.features.sensorpanels.domain.Panel
import edu.iesam.meceiot.features.sensorpanels.domain.Sensor

fun Sensor.toUiModel(): SensorUiModel {
    return SensorUiModel(
        id = id,
        name = name,
        panelName = panelName,
        query = query
    )
}

fun Panel.toUiModel(): PanelUiModel {
    return PanelUiModel(
        id = id,
        name = name,
        sensors = sensors.map { it.toUiModel() }
    )
}