package edu.iesam.meceiot.features.externalresources.data.remote

import edu.iesam.meceiot.features.externalresources.data.remote.ExternalResourcesService
import edu.iesam.meceiot.features.externalresources.domain.ExternalResources
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ExternalResourcesRemoteDataSource(private val externalResourcesService: ExternalResourcesService) {
    suspend fun getAllExternalResources(): List<ExternalResources> {
        val response = externalResourcesService.getAllExternalResources()
        return if (response.isSuccessful) {
            response.body() ?: emptyList()
        } else {
            emptyList()
        }
    }

}