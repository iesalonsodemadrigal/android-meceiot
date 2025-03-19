package edu.iesam.meceiot.features.grafana.data.models.sensordata

data class InfluxQueryRequestDto(
    val queries: List<InfluxQueryDto>,
    val from: String,
    val to: String
)

// Representa cada consulta dentro de "queries"
data class InfluxQueryDto(
    val query: String,
    val refId: String,
    val datasourceId: Int,
    val intervalMs: Int,
    val maxDataPoints: Int
)