package edu.iesam.meceiot.features.alerts.data.remote.models

import com.google.gson.annotations.SerializedName

data class PanelDetailGrafanaModel(
    @SerializedName("dashboard") val dashboard: DashboardGrafanaModel,
    @SerializedName("uid") val uid: String,
    @SerializedName("title") val title: String
)

data class DashboardGrafanaModel(
    @SerializedName("panels") val panels: List<SensorsDashboardGrafanaModel>
)

data class SensorsDashboardGrafanaModel(
    @SerializedName("id") val id: Int,
    @SerializedName("targets") val targets: List<TargetsDashboardGrafanaModel>
)

data class TargetsDashboardGrafanaModel(
    @SerializedName("query") val query: String,
    @SerializedName("refId") val refId: String
)