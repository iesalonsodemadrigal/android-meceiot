package edu.iesam.meceiot.core.data

import edu.iesam.meceiot.features.externalresources.data.api.ExternalResourcesService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val RESOURCES_URL = "https://sandbox.aulapragmatica.es/resources/"

    //Retrofit For External Resources
    private fun provideExternalResourcesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(RESOURCES_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideExternalResourcesService(): ExternalResourcesService {
        return provideExternalResourcesRetrofit().create(ExternalResourcesService::class.java)
    }

}