package edu.iesam.meceiot.features.lorawan.data.remote

import com.google.gson.annotations.SerializedName

data class LoraWanApiModel(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("image") val image: String,
    @SerializedName("description") val description: String
)