package edu.iesam.meceiot.features.grafana.data.remote


import edu.iesam.meceiot.features.grafana.data.remote.grafanamodel.MetaGrafanaModelUid
import edu.iesam.meceiot.features.grafana.data.remote.grafanamodel.PanelGrafanaModel
import retrofit2.Response
import retrofit2.http.GET

interface GrafanaService {

    @GET("api/search?type=dash-db")
    suspend fun getPanels(): Response<List<PanelGrafanaModel>>

    @GET("api/search?type=dash-db")
    suspend fun getSensorsPanel(): Response<MetaGrafanaModelUid>

    @GET("api/search?type=dash-db")
    suspend fun getSensorDetail(): Response<MetaGrafanaModelUid>
}