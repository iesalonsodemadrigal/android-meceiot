package edu.iesam.meceiot.core.data

import edu.iesam.meceiot.features.lorawan.data.remote.LoraWanApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL_LORAWAN_INFO =
        "https://sandbox.aulapragmatica.es/meceiot/api/lorawan/"

    private fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_LORAWAN_INFO)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideInfoLorawanService(): LoraWanApiService {
        return provideRetrofit().create(LoraWanApiService::class.java)
    }
}