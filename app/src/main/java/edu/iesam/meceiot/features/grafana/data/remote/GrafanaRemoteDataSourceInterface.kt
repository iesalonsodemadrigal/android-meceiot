package edu.iesam.meceiot.features.grafana.data.remote

import edu.iesam.meceiot.features.alerts.domain.Alert
import edu.iesam.meceiot.features.grafana.data.models.search.DashboardSummary
import edu.iesam.meceiot.features.grafana.data.models.sensordata.InfluxQueryRequestDto
import edu.iesam.meceiot.features.grafana.data.models.sensordata.InfluxQueryResponseDto
import edu.iesam.meceiot.features.pantallasensor.domain.GraphSensor
import edu.iesam.meceiot.features.sensorpanels.domain.Panel

interface GrafanaRemoteDataSourceInterface {
    suspend fun searchDashboards(): Result<List<DashboardSummary>>
    suspend fun getDashboardDetail(uid: String): Result<Panel>
    suspend fun queryData(body: InfluxQueryRequestDto): Result<List<InfluxQueryResponseDto>>
    suspend fun getSensorPanels(): Result<List<Panel>>
    suspend fun getSensorData(query: String, from: Long, to: Long): Result<GraphSensor>
    suspend fun getSensorAlerts(): Result<List<Alert>>
}