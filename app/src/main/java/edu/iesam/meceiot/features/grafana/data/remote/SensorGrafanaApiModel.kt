package edu.iesam.meceiot.features.grafana.data.remote

import com.google.gson.annotations.SerializedName


data class PanelGrafanaModel(
    @SerializedName("id") val id: Int,
    @SerializedName("uid") val uid: String,
    @SerializedName("title") val title: String, //este le utilizo, el nombre del panel(zona)
)
//Como relaciono el PanelGrafanaModel y el MetaGrafanaModelUid, me refiero a que atributos debo poner

data class MetaGrafanaModelUid(
    @SerializedName("dashboard") val id: DashboardGrafanaModel,
)


data class DashboardGrafanaModel(
    @SerializedName("panels") val panels: PanelsDashboardGrafanaModel, //lista de paneles del Alonso?
)

data class PanelsDashboardGrafanaModel(
    @SerializedName("id") val id: String,
    @SerializedName("targets") val targets: TargetsDashboardGrafanaModel,
    @SerializedName("from") val dataFrom: String
)

data class TargetsDashboardGrafanaModel(
    @SerializedName("query") val query: QueryResultsDashboardGrafanaModel, //la consulta del sensor?
    @SerializedName("refId") val nameSensor: String, //nombre del sensor
)

// De aquí abajo saco el dato que se mide (value) que necesito
data class QueryResultsDashboardGrafanaModel(
    @SerializedName("frames") val frames: FrameQueryResultDashboardGrafanaModel
)

data class FrameQueryResultDashboardGrafanaModel(
    @SerializedName("schema") val schema: SchemaFrameQueryResultDashboardGrafanaModel,
    @SerializedName("data") val data: DataFrameQueryResultDashboardGrafanaModel
)

data class SchemaFrameQueryResultDashboardGrafanaModel(
    @SerializedName("meta") val meta: MetaSchemaFrameQueryResultDashboardGrafanaModel
)

//En fields hay un atributo "name": "co2" debería coger ese como nombre de sensor o el refId?
data class MetaSchemaFrameQueryResultDashboardGrafanaModel(
    @SerializedName("executedQueryString") val meta: String // "from(bucket: \"Meceiot\")\r\n  |> range(start: 2024-11-08T10:28:04.889Z, stop: 2024-11-08T16:28:04.889Z)\r\n  |> filter(fn: (r) => r[\"_measurement\"] == \"co2-009-a81758fffe0be3ba\")\r\n  |> filter(fn: (r) => r[\"_field\"] == \"co2\")\r\n  |> aggregateWindow(every: 20s, fn: mean, createEmpty: false)\r\n  |> yield(name: \"mean\")"
)

data class DataFrameQueryResultDashboardGrafanaModel(
    @SerializedName("values") val values: String //o debería ser un List?? o necesito el último dato para mostrar o no alerta
)