package edu.iesam.meceiot.features.grafana.data.remote

import com.google.gson.annotations.SerializedName


data class PanelGrafanaModel(
    @SerializedName("id") val id: Int,
    @SerializedName("uid") val uid: String,
    @SerializedName("title") val title: String,
)

data class MetaGrafanaModelUid(
    @SerializedName("dashboard") val id: DashboardGrafanaModel,
)


data class DashboardGrafanaModel(
    @SerializedName("panels") val panels: PanelsDashboardGrafanaModel,
)

data class PanelsDashboardGrafanaModel(
    @SerializedName("id") val id: String,
    @SerializedName("targets") val targets: TargetsDashboardGrafanaModel,
)

data class TargetsDashboardGrafanaModel(
    @SerializedName("query") val query: String,
    @SerializedName("refId") val refId: String,
)
