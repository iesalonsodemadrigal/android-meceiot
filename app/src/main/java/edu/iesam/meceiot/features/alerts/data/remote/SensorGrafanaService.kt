package edu.iesam.meceiot.features.alerts.data.remote

import edu.iesam.meceiot.features.alerts.data.remote.models.BodyQueryModel
import edu.iesam.meceiot.features.alerts.data.remote.models.PanelDetailGrafanaModel
import edu.iesam.meceiot.features.alerts.data.remote.models.PanelsGrafanaModel
import edu.iesam.meceiot.features.alerts.data.remote.models.SensorQueryResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SensorGrafanaService {

    @GET("api/search?type=dash-db")
    suspend fun getPanels(): Response<List<PanelsGrafanaModel>>

    @GET("api/dashboards/uid/{uid}")
    suspend fun getPanelDetail(@Path("uid") uid: String): Response<PanelDetailGrafanaModel>

    @POST("api/ds/query?ds_type=influxdb&requestId=Q107")
    suspend fun getQuerySensor(@Body bodyQueryModel: BodyQueryModel): Response<SensorQueryResponseModel>
}