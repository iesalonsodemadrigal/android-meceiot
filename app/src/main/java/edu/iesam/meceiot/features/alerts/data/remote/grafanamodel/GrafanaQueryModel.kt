package edu.iesam.meceiot.features.alerts.data.remote.grafanamodel

import com.google.gson.annotations.SerializedName

// GRAFANA MODEL BODY QUERY

// Modelo para la consulta directa a Grafana
data class BodyQueryModel(
    @SerializedName("queries") val queries: List<QueriesBodyQueryModel>,
    @SerializedName("from") val from: String,
    @SerializedName("to") val to: String
)

// Detalles de la consulta enviada a Grafana
data class QueriesBodyQueryModel(
    @SerializedName("query") val query: String,
    @SerializedName("refId") val refId: String,
    @SerializedName("datasourceId") val datasourceId: Int,
    @SerializedName("intervalMs") val intervalMs: Int,
    @SerializedName("maxDataPoints") val maxDataPoints: Int
)

// GRAFANA MODEL RESPONSE QUERY

// Respuesta de la consulta de datos a Grafana
data class QuerySensorResponseModel(
    @SerializedName("results") val results: Map<String, ResultsQueryModel>
)

data class ResultsQueryModel(
    @SerializedName("frames") val frames: List<FramesResultsQueryModel>
)

// Contiene los valores medidos por el sensor
data class FramesResultsQueryModel(
    @SerializedName("data") val data: DataFramesResultsQueryModel
)

// Datos extraídos de la consulta, contiene los valores medidos
data class DataFramesResultsQueryModel(
    @SerializedName("values") val values: List<List<Long>>
) {
    fun getLastValue(): Long? = values.getOrNull(1)?.lastOrNull() // Devuelve el último valor medido
}