package edu.iesam.meceiot.features.externalresources.data.api

import edu.iesam.meceiot.features.externalresources.domain.ExternalResources
import retrofit2.Response
import retrofit2.http.GET

interface ExternalResourcesService {
    @GET("resources.json")
    suspend fun getAllExternalResources(): Response<List<ExternalResources>>
}