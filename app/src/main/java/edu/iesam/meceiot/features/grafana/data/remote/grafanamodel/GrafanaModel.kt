package edu.iesam.meceiot.features.grafana.data.remote.grafanamodel

import com.google.gson.annotations.SerializedName

// Modelo de Panel en Grafana (representa una zona)
data class PanelGrafanaModel(
    @SerializedName("id") val id: Int,
    @SerializedName("uid") val uid: String,
    @SerializedName("title") val title: String // Nombre del panel (zona)
)

// Modelo de Dashboard, que contiene los paneles (zonas)
data class DashboardGrafanaModel(
    @SerializedName("panels") val panels: List<SensorDashboardGrafanaModel>
)

// Modelo que relaciona un Dashboard con su UID (metadatos)
data class MetaGrafanaModelUid(
    @SerializedName("dashboard") val dashboard: DashboardGrafanaModel
)

// Representa un sensor dentro de un panel
data class SensorDashboardGrafanaModel(
    @SerializedName("id") val id: Int,
    @SerializedName("targets") val targets: List<TargetsDashboardGrafanaModel>
)

// Representa la consulta de datos del sensor dentro de Grafana
data class TargetsDashboardGrafanaModel(
    @SerializedName("query") val query: String, // Consulta del sensor
    @SerializedName("refId") val nameSensor: String // Nombre del sensor
)

// Respuesta de la consulta de datos a Grafana
data class QuerySensor(
    @SerializedName("results") val results: ResultQueryModel
)

data class ResultQueryModel(
    @SerializedName("refId") val refId: FramesQueryResultDashboardGrafanaModel
)

// Contiene los valores medidos por el sensor
data class FramesQueryResultDashboardGrafanaModel(
    @SerializedName("data") val data: DataFrameQueryResultDashboardGrafanaModel
)

// Datos extraídos de la consulta, contiene los valores medidos
data class DataFrameQueryResultDashboardGrafanaModel(
    @SerializedName("values") val values: List<List<Long>>
) {
    fun getLastValue(): List<Long>? = values.lastOrNull() // Devuelve el último valor medido
}

// Modelo para la consulta directa a Grafana
data class BodyQueryModel(
    val queries: QueryRequestModel,
    val from: String,
    val to: String
)

// Detalles de la consulta enviada a Grafana
data class QueryRequestModel(
    val query: String,
    val data: Int,
    val interval: Int,
    val maxData: Int
)
