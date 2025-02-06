package edu.iesam.meceiot.features.grafana.data.remote

import edu.iesam.meceiot.core.data.remote.apiCall
import org.koin.core.annotation.Single

@Single
class GrafanaApiRemoteDataSource(private val grafanaService: GrafanaService) {

    suspend fun getSensorsGrafana(): Result<List<PanelModel>> {
        return apiCall { grafanaService.getPanels() }.map { sensorsGrafanaApiModel ->
            sensorsGrafanaApiModel.map { it.toModel() }
        }
    }
}