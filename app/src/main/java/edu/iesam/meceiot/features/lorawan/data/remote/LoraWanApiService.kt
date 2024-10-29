package edu.iesam.meceiot.features.lorawan.data.remote

import edu.iesam.meceiot.features.lorawan.domain.LoraWanInfo
import retrofit2.Response
import retrofit2.http.GET

interface LoraWanApiService {

    @GET("lorawan_info.json")
    suspend fun getLoraWanInfo(): Response<List<LoraWanInfo>>
}