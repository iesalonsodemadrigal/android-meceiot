package edu.iesam.meceiot.features.grafana.data.remote

import edu.iesam.meceiot.features.grafana.data.models.dashboard.DashboardDetailResponseDto
import edu.iesam.meceiot.features.grafana.data.models.search.DashboardResponseDto
import edu.iesam.meceiot.features.grafana.data.models.sensordata.InfluxQueryRequestDto
import edu.iesam.meceiot.features.grafana.data.models.sensordata.InfluxQueryResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface GrafanaService {
    @GET("api/search")
    suspend fun searchDashboards(
        @Query("type") type: String = "dash-db"
    ): Response<List<DashboardResponseDto>>

    @GET("api/dashboards/uid/{uid}")
    suspend fun getDashboardByUid(
        @Path("uid") uid: String,
    ): Response<DashboardDetailResponseDto>

    @POST("api/ds/query")
    suspend fun queryData(
        @Query("ds_type") dsType: String = "influxdb",
        @Query("requestId") requestId: String = "Q107",
        @Body body: InfluxQueryRequestDto
    ): Response<InfluxQueryResponseDto>
}