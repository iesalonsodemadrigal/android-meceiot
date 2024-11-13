package edu.iesam.meceiot.features.developer.data.remote


import retrofit2.Response
import retrofit2.http.GET

interface DeveloperApiService {
    @GET("meceiot/api/developer/developers.json")
    suspend fun getDevelopers(): Response<List<DeveloperApiModel>>
}




