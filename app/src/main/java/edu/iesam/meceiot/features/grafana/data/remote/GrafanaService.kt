package edu.iesam.meceiot.features.grafana.data.remote

import edu.iesam.meceiot.features.grafana.data.models.DashboardDetailResponseDto
import edu.iesam.meceiot.features.grafana.data.models.DashboardResponseDto
import retrofit2.Response
import retrofit2.http.GET
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
}