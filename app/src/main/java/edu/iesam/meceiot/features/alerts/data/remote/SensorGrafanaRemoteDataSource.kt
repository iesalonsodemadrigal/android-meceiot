package edu.iesam.meceiot.features.alerts.data.remote

import edu.iesam.meceiot.features.alerts.data.remote.mappers.extractTypeFromRefId
import edu.iesam.meceiot.features.alerts.data.remote.mappers.toDomain
import edu.iesam.meceiot.features.alerts.data.remote.models.BodyQueryModel
import edu.iesam.meceiot.features.alerts.data.remote.models.QueriesBodyQueryModel
import edu.iesam.meceiot.features.alerts.domain.Sensor
import org.koin.core.annotation.Single

@Single
class SensorGrafanaRemoteDataSource(private val sensorGrafanaService: SensorGrafanaService) {
    suspend fun getSensors(): Result<List<Sensor>> {
        val panelsGrafana = sensorGrafanaService.getPanels()

        val panels =
            panelsGrafana.body() ?: return Result.failure(Exception("No hay paneles disponibles"))
        val sensorsList = mutableListOf<Sensor>()

        for (panel in panels) {
            val panelDetailGrafana = sensorGrafanaService.getPanelDetail(panel.uid)
            val panelDetail = panelDetailGrafana.body() ?: continue

            val updatedSensors = panelDetail.dashboard.panels.flatMap { sensorDashboard ->
                sensorDashboard.targets.map { target ->
                    val sensor = Sensor(
                        id = target.refId,
                        name = panel.title,
                        type = extractTypeFromRefId(target.refId),
                        value = ""
                    )

                    val bodyQuery = creteBodyQuery(target.query, target.refId)
                    val sensorQueryResult = sensorGrafanaService.getQuerySensor(bodyQuery)
                    val newValueSensor =
                        sensorQueryResult.body()?.toDomain(target.refId)?.value ?: ""
                    sensor.copy(value = newValueSensor)
                }
            }
            sensorsList.addAll(updatedSensors)
        }
        return Result.success(sensorsList)
    }

    private fun creteBodyQuery(query: String, refId: String): BodyQueryModel {
        return BodyQueryModel(
            queries = listOf(
                QueriesBodyQueryModel(
                    query = query,
                    refId = refId,
                    datasourceId = 4,
                    intervalMs = 20000,
                    maxDataPoints = 935
                )
            ),
            from = "now-10m",
            to = "now"
        )
    }
}