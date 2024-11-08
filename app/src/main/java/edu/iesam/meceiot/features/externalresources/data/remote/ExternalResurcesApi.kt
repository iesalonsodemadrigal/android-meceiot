package edu.iesam.meceiot.features.externalresources.data.remote

import com.google.gson.annotations.SerializedName

data class ExternalResourcesApiModel(
    @SerializedName("data") val resourceName: String,
    @SerializedName("description") val resourceDescription: String,
    @SerializedName("image") val resourceImage: String,
    @SerializedName("url") val resourceUrl: String
)