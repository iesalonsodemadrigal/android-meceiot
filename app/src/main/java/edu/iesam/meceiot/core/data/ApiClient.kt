package edu.iesam.meceiot.core.data

import edu.iesam.meceiot.features.lorawan.data.remote.LoraWanApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL_LORAWAN_INFO =
        "https://sandbox.aulapragmatica.es/meceiot/api/lorawan/"

    private val serviceMap = mutableMapOf<String, Any>()
    private fun <T> createService(
        baseUrl: String,
        serviceClass: Class<T>
    ): T {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(serviceClass)
    }

    private fun <T : Any> provideService(baseUrl: String, serviceClass: Class<T>): T {
        return serviceMap[baseUrl] as? T ?: createService(
            baseUrl,
            serviceClass
        ).also { serviceMap[baseUrl] = it }
    }

    fun provideInfoLorawanService(): LoraWanApiService {
        return provideService(BASE_URL_LORAWAN_INFO, LoraWanApiService::class.java)
    }
}