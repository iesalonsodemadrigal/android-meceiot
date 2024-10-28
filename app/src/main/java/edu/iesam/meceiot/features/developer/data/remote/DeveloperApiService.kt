package edu.iesam.meceiot.features.developer.data.remote


import edu.iesam.meceiot.features.developer.domain.models.DevelopersResponse
import retrofit2.Response


import retrofit2.http.GET
import retrofit2.http.Query


interface DeveloperApiService {

    @GET("developers")
    suspend fun getDevelopers(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<DevelopersResponse>


}