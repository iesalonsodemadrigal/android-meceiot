package edu.iesam.meceiot.features.externalresources.data.remote

import retrofit2.http.GET

interface ExternalResourcesService {
    @GET("/external-resources")
    suspend fun getAllExternalResources(): List<ExternalResourcesApiModel>

}