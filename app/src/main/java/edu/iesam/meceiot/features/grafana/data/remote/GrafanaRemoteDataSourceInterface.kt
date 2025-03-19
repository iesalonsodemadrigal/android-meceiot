package edu.iesam.meceiot.features.grafana.data.remote

import edu.iesam.meceiot.features.grafana.data.models.DashboardSummary
import edu.iesam.meceiot.features.sensorpanels.domain.Panel

interface GrafanaRemoteDataSourceInterface {
    suspend fun searchDashboards(): Result<List<DashboardSummary>>
    suspend fun getDashboardDetail(uid: String): Result<Panel>
    suspend fun getSensorPanels(): Result<List<Panel>>
}