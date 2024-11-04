package edu.iesam.meceiot.features.externalresources.data.remote

import edu.iesam.meceiot.features.externalresources.domain.ExtrenalResources
import retrofit2.http.GET

interface ExternalResourcesService {
    @GET("https://sandbox.aulapragmatica.es/resources/resources.json")
    suspend fun getAll(): List<ExtrenalResources>
}