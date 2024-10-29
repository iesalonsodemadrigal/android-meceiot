package edu.iesam.meceiot.features.developer.data.remote


import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo

import retrofit2.Response
import retrofit2.http.GET

interface DeveloperApiService {
    @GET("developers/2024/developers.json")
    suspend fun getDevelopers(): Response<List<DeveloperInfo>>
}




