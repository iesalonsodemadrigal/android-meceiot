package edu.iesam.meceiot.features.alerts.data.remote

import edu.iesam.meceiot.core.domain.ErrorApp
import edu.iesam.meceiot.features.alerts.data.remote.mappers.extractTypeFromRefId
import edu.iesam.meceiot.features.alerts.data.remote.mappers.sensorToDomain
import edu.iesam.meceiot.features.alerts.data.remote.mappers.toDomain
import edu.iesam.meceiot.features.alerts.data.remote.models.BodyQueryModel
import edu.iesam.meceiot.features.alerts.data.remote.models.QueriesBodyQueryModel
import edu.iesam.meceiot.features.alerts.domain.Sensor
import org.koin.core.annotation.Single

@Single
class SensorGrafanaRemoteDataSource(private val sensorGrafanaService: SensorGrafanaService) {
    suspend fun getSensors(): Result<List<Sensor>> {
        val panelsGrafana = sensorGrafanaService.getPanels().body()
            ?: return Result.failure(ErrorApp.ServerErrorApp)
        val sensorsList = mutableListOf<Sensor>()

        for (panel in panelsGrafana) {
            val panelDetailGrafana =
                sensorGrafanaService.getPanelDetail(panel.uid).body() ?: continue

            val newSensor = panelDetailGrafana.dashboard.panels.flatMap { sensorDashboard ->
                sensorDashboard.targets.firstOrNull()?.let { target ->
                    val bodyQuery = creteBodyQuery(target.query, target.refId)
                    val sensorQueryResult = sensorGrafanaService.getQuerySensor(bodyQuery)
                    val lastValue = sensorQueryResult.body()?.toDomain(target.refId)?.value
                        ?: "No data"

                    listOf(
                        sensorToDomain(
                            target.refId,
                            panel.title,
                            extractTypeFromRefId(target.refId),
                            lastValue
                        )
                    )
                } ?: emptyList()
            }
            sensorsList.addAll(newSensor)
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
            ), from = "now-10m", to = "now"
        )
    }
}