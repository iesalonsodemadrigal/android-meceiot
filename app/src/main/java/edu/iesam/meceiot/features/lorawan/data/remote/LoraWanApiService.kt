package edu.iesam.meceiot.features.lorawan.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface LoraWanApiService {

    @GET("meceiot/api/lorawan/lorawan_info.json")
    suspend fun getLoraWanInfo(): Response<List<LoraWanApiModel>>
}