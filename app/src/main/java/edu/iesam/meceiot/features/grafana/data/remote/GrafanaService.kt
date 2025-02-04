package edu.iesam.meceiot.features.grafana.data.remote

import okhttp3.Response
import retrofit2.http.GET

interface GrafanaService {


    @GET("api/search?type=dash-db")
    suspend fun getPanels(): Response<List<Panel>>
}