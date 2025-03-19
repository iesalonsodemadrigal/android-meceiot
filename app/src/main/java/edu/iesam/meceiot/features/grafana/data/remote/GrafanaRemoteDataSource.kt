package edu.iesam.meceiot.features.grafana.data.remote

import edu.iesam.meceiot.core.data.remote.apiCall
import edu.iesam.meceiot.features.grafana.data.models.DashboardSummary
import edu.iesam.meceiot.features.grafana.data.models.toDashboardSummary
import edu.iesam.meceiot.features.grafana.data.models.toPanel
import edu.iesam.meceiot.features.sensorpanels.domain.Panel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.koin.core.annotation.Single

@Single
class GrafanaRemoteDataSource(
    private val grafana: GrafanaService
) : GrafanaRemoteDataSourceInterface {

    override suspend fun searchDashboards(): Result<List<DashboardSummary>> {
        return apiCall { grafana.searchDashboards() }
            .fold(
                onSuccess = { dashboardDtos ->
                    // Convertir a resumen para conservar el uid
                    Result.success(dashboardDtos.map { it.toDashboardSummary() })
                },
                onFailure = { error ->
                    Result.failure(error)
                }
            )
    }

    override suspend fun getDashboardDetail(uid: String): Result<Panel> {
        return apiCall { grafana.getDashboardByUid(uid) }
            .fold(
                onSuccess = { detailDto ->
                    Result.success(detailDto.toPanel())
                },
                onFailure = { error ->
                    Result.failure(error)
                }
            )
    }

    override suspend fun getSensorPanels(): Result<List<Panel>> {
        // Paso 1: obtener listado de resúmenes
        return searchDashboards().fold(
            onSuccess = { summaries ->
                // Paso 2: para cada dashboard resumido, obtener el detalle (incluyendo sensores)
                val panels: List<Panel> = try {
                    coroutineScope {
                        summaries.map { summary ->
                            async {
                                // Llamamos al endpoint de detalle usando el uid
                                getDashboardDetail(summary.uid)
                                    .fold(
                                        onSuccess = { detailPanel ->
                                            // Si el detalle es correcto, usamos el Panel obtenido
                                            detailPanel
                                        },
                                        onFailure = { _ ->
                                            // En caso de fallo, devolvemos un Panel con sensores vacíos
                                            Panel(
                                                id = summary.id,
                                                name = summary.title,
                                                sensors = emptyList()
                                            )
                                        }
                                    )
                            }
                        }.awaitAll()
                    }
                } catch (e: Exception) {
                    // Si falla alguna de las llamadas concurrentes
                    return Result.failure(e)
                }
                // Paso 3: devolver la lista de paneles
                Result.success(panels)
            },
            onFailure = { error ->
                Result.failure(error)
            }
        )
    }
}