package edu.iesam.meceiot.features.grafana.data.remote

import edu.iesam.meceiot.core.data.remote.apiCall
import edu.iesam.meceiot.features.grafana.data.remote.grafanamodel.MetaGrafanaModelUid
import org.koin.core.annotation.Single

@Single
class GrafanaRemoteDataSource(private val grafanaService: GrafanaService) {

    suspend fun getSensors(): Result<MetaGrafanaModelUid> { //Result<Sensor>>
        return apiCall {
            grafanaService.getSensorsPanel()
        }
    }
}