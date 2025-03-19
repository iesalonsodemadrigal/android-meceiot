package edu.iesam.meceiot.features.grafana.data.models

// Clase interna para conservar el uid y demás datos del dashboard
data class DashboardSummary(
    val id: Int,
    val uid: String,
    val title: String
)

fun DashboardResponseDto.toDashboardSummary(): DashboardSummary =
    DashboardSummary(
        id = this.id,
        uid = this.uid,
        title = this.title
    )