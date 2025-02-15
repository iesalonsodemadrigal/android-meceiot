package edu.iesam.meceiot.features.alerts.data.remote.mappers

import edu.iesam.meceiot.features.alerts.data.remote.models.PanelDetailGrafanaModel
import edu.iesam.meceiot.features.alerts.domain.Panel


fun PanelDetailGrafanaModel.toDomain(): Panel {
    return Panel(id = uid, name = title, sensors = emptyList())
}