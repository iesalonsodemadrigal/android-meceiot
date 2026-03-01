package edu.iesam.meceiot.features.grafana.data.models.dashboard

import com.google.gson.annotations.SerializedName
import edu.iesam.meceiot.features.sensorpanels.domain.Panel
import edu.iesam.meceiot.features.sensorpanels.domain.Sensor

// Datos: DTOs para el endpoint de detalle
data class DashboardDetailResponseDto(
    @SerializedName("meta") val meta: MetaDto,
    @SerializedName("dashboard") val dashboard: DashboardDetailDto
)

data class MetaDto(
    @SerializedName("type") val type: String // se pueden agregar más campos si es necesario
)

data class DashboardDetailDto(
    @SerializedName("id") val id: Int,
    @SerializedName("uid") val uid: String,
    @SerializedName("title") val title: String,
    @SerializedName("panels") val panels: List<PanelDetailDto>
)

data class PanelDetailDto(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("targets") val targets: List<TargetDto>,
    @SerializedName("fieldConfig") val fieldConfig: Map<String, Any>? = null
)


data class TargetDto(
    @SerializedName("refId") val refId: String,
    @SerializedName("query") val query: String
)

fun DashboardDetailResponseDto.toPanel(): Panel {
    return Panel(
        id = dashboard.id,
        name = dashboard.title,
        sensors = dashboard.panels.flatMap { panelDetail ->
            panelDetail.targets.mapIndexed { index, target ->
                // Tratar de obtener el displayName y la unidad desde el mapa dinámico fieldConfig
                val defaultsMap = panelDetail.fieldConfig?.get("defaults") as? Map<String, Any>
                val unit = defaultsMap?.get("unit") as? String
                val extractedDisplayName = defaultsMap?.get("displayName") as? String

                val sensorName = extractedDisplayName ?: when (unit) {
                    "pressurehpa" -> "Presión [hPa]"
                    "conppb" -> "VOC [ppb]"
                    "radbq" -> if (target.query.contains("radon_long")) "Radón Largo [Bq/m³]" else if (target.query.contains(
                            "radon_short"
                        )
                    ) "Radón Corto [Bq/m³]" else "Radón [Bq/m³]"

                    else -> target.refId
                }

                Sensor(
                    id = panelDetail.id * 100 + index, //unique id for more targets
                    name = sensorName, // Se usa el displayName, o la unidad como fallback, sino el refId
                    panelName = dashboard.title,
                    query = target.query
                )
            }
        }
    )
}