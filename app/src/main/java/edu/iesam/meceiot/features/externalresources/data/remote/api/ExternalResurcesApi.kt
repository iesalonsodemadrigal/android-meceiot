package edu.iesam.meceiot.features.externalresources.data.remote.api

import com.google.gson.annotations.SerializedName

data class ExternalResourcesApiModel(
    @SerializedName("author") val author: String,
    @SerializedName("name_resource") val nameResource: String,
    @SerializedName("icon_source") val iconSource: String,
    @SerializedName("link_resource") val linkResource: String,
    @SerializedName("id_external_resources") val idExternalResources: String
)