package edu.iesam.meceiot.features.alerts.data.remote.models

import com.google.gson.annotations.SerializedName

// GRAFANA MODEL BODY QUERY
data class BodyQueryModel(
    @SerializedName("queries") val queries: List<QueriesBodyQueryModel>,
    @SerializedName("from") val from: String,
    @SerializedName("to") val to: String
)

data class QueriesBodyQueryModel(
    @SerializedName("query") val query: String,
    @SerializedName("refId") val refId: String,
    @SerializedName("datasourceId") val datasourceId: Int,
    @SerializedName("intervalMs") val intervalMs: Int,
    @SerializedName("maxDataPoints") val maxDataPoints: Int
)

// GRAFANA MODEL RESPONSE QUERY
data class SensorQueryResponseModel(
    @SerializedName("results") val results: Map<String, ResultsQueryModel>
)

data class ResultsQueryModel(
    @SerializedName("frames") val frames: List<FramesResultsQueryModel>
)

data class FramesResultsQueryModel(
    @SerializedName("data") val data: DataFramesResultsQueryModel
)

data class DataFramesResultsQueryModel(
    @SerializedName("values") val values: List<List<Any>>
)