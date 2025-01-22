package edu.iesam.meceiot.features.alerts.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface SensorAlertService {

    @GET("/alerts.json")
    suspend fun getSensors(): Response<List<PanelApiModel>>
}