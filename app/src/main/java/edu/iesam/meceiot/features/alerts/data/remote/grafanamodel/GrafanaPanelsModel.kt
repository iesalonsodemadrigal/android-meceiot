package edu.iesam.meceiot.features.alerts.data.remote.grafanamodel

import com.google.gson.annotations.SerializedName

// Modelo de Panel en Grafana (representa una zona)
data class PanelsGrafanaModel(
    @SerializedName("id") val id: Int,
    @SerializedName("uid") val uid: String,
    @SerializedName("title") val title: String // Nombre del panel (zona)
)