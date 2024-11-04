package edu.iesam.meceiot.features.externalresources.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ExternalResourcesRemoteDataSource {
    object RetrofitInstance {
        val api: ExternalResourcesService by lazy {
            Retrofit.Builder()
                .baseUrl("https://sandbox.aulapragmatica.es/resources/resources.json")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ExternalResourcesService::class.java)
        }
    }
}