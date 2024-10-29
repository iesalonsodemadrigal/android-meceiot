package edu.iesam.meceiot.core.data.api

import edu.iesam.meceiot.features.developer.data.remote.DeveloperApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    const val BASE_URL_DEVELOPERS = "https://sandbox.aulapragmatica.es/"

    private val serviceMap = mutableMapOf<String, Any>()
    private fun <T> createService(baseUrl: String, serviceClass: Class<T>): T {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build().create(serviceClass)
    }

    private fun <T : Any> provideService(baseUrl: String, serviceClass: Class<T>): T {
        return serviceMap[baseUrl] as? T ?: createService(
            baseUrl,
            serviceClass
        ).also { serviceMap[baseUrl] = it }
    }

    fun provideDeveloperService(): DeveloperApiService {
        return provideService(BASE_URL_DEVELOPERS, DeveloperApiService::class.java)
    }


}