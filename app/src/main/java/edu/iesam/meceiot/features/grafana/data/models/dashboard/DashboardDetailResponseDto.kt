package edu.iesam.meceiot.features.grafana.data.models.dashboard

import edu.iesam.meceiot.features.sensorpanels.domain.Panel
import edu.iesam.meceiot.features.sensorpanels.domain.Sensor

// Datos: DTOs para el endpoint de detalle
data class DashboardDetailResponseDto(
    val meta: MetaDto,
    val dashboard: DashboardDetailDto
)

data class MetaDto(
    val type: String // se pueden agregar más campos si es necesario
)

data class DashboardDetailDto(
    val id: Int,
    val uid: String,
    val title: String,
    val panels: List<PanelDetailDto>
)

data class PanelDetailDto(
    val id: Int,
    val title: String,
    val targets: List<TargetDto>
)

data class TargetDto(
    val refId: String,
    val query: String
)

fun DashboardDetailResponseDto.toPanel(): Panel {
    return Panel(
        id = dashboard.id,
        name = dashboard.title,
        sensors = dashboard.panels.flatMap { panelDetail ->
            panelDetail.targets.mapIndexed { index, target ->
                Sensor(
                    id = panelDetail.id * 100 + index, //unique id for more targets
                    name = target.refId, // Se usa el refId del target como nombre
                    panelName = dashboard.title,
                    query = target.query
                )
            }
        }
    )
}