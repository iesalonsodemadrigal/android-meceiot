package edu.iesam.meceiot.features.alerts.data.remote.mappers

import edu.iesam.meceiot.features.alerts.data.remote.models.PanelsGrafanaModel
import edu.iesam.meceiot.features.alerts.domain.Panel

fun PanelsGrafanaModel.toDomain(): Panel {
    return Panel(
        id = uid,
        name = title,
        sensors = emptyList()
    )
}