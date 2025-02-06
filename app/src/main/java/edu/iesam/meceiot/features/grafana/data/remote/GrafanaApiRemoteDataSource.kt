package edu.iesam.meceiot.features.grafana.data.remote

import edu.iesam.meceiot.core.data.remote.apiCall
import edu.iesam.meceiot.features.alerts.domain.Sensor

//@Single
class GrafanaApiRemoteDataSource(private val grafanaService: GrafanaService) {

    supend
    fun getSensorsGrafana(): Result<List<Sensor>> {
        return apiCall { grafanaService.getPanels() }.map { sensorsGrafanaApiModel ->
            sensorsGrafanaApiModel.map { it.toModel() }
        }
    }
}