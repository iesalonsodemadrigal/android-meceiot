package edu.iesam.meceiot.features.developer.data.remote

import com.google.gson.annotations.SerializedName

data class DeveloperApiModel(
    @SerializedName("id")  val id: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("college_degree") val collegeDegree: String,
    @SerializedName("url_avatar") val urlAvatar: String,
    @SerializedName("url_source") val urlSource: String
)
