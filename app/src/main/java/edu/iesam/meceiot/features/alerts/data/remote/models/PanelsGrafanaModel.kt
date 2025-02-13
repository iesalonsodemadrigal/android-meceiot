package edu.iesam.meceiot.features.alerts.data.remote.models

import com.google.gson.annotations.SerializedName

data class PanelsGrafanaModel(
    @SerializedName("uid") val uid: String,
    @SerializedName("title") val title: String
)