package edu.iesam.meceiot.features.sensorpanels.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface SensorPanelsService {
    @GET("panels")
    suspend fun getSensorPanels(): Response<List<PanelApiModel>>
}