package edu.iesam.meceiot.features.sensorpanels.data.remote

import com.google.gson.annotations.SerializedName

data class SensorApiModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)
