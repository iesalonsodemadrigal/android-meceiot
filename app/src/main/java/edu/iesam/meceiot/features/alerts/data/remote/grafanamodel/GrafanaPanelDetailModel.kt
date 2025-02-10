package edu.iesam.meceiot.features.alerts.data.remote.grafanamodel

import com.google.gson.annotations.SerializedName


// Modelo de Dashboard, que contiene los paneles (zonas)
data class PanelDetailGrafanaModel(
    @SerializedName("dashboard") val dashboard: DashboardGrafanaModel,
    @SerializedName("uid") val uid: String,
    @SerializedName("title") val title: String
)

// Lista de sensores
data class DashboardGrafanaModel(
    @SerializedName("panels") val panels: List<SensorsDashboardGrafanaModel>
)

// Representa un sensor dentro de un panel
data class SensorsDashboardGrafanaModel(
    @SerializedName("id") val id: Int,
    @SerializedName("targets") val targets: List<TargetsDashboardGrafanaModel>
)

// Representa la consulta de datos del sensor dentro de Grafana
data class TargetsDashboardGrafanaModel(
    @SerializedName("query") val query: String, // Consulta del sensor
    @SerializedName("refId") val refId: String // Nombre del sensor
)