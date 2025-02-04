package edu.iesam.meceiot.features.externalresources.data.remote.api

import retrofit2.Response
import retrofit2.http.GET

interface ExternalResourcesService {
    @GET("resources/resources.json")
    suspend fun getAllExternalResources(): Response<List<ExternalResourcesApiModel>>

}