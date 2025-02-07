package edu.iesam.meceiot.features.pantallasensor.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface GraphSensorService {
    @GET("sensor")
    suspend fun getSensorData(): Response<SensorGraphApiModel>
}