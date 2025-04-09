package edu.iesam.meceiot.features.alerts.data

import edu.iesam.meceiot.features.alerts.data.local.AlertDbLocalDataSource
import edu.iesam.meceiot.features.alerts.domain.Alert
import edu.iesam.meceiot.features.alerts.domain.AlertRepository
import edu.iesam.meceiot.features.grafana.data.remote.GrafanaRemoteDataSource
import org.koin.core.annotation.Single

@Single
class AlertDataRepository(
    private val local: AlertDbLocalDataSource,
    private val remote: GrafanaRemoteDataSource
) : AlertRepository {
    override suspend fun getAlerts(): Result<List<Alert>> {
        val localResult = local.getAllAlerts()
        return if (localResult.isFailure) {
            val remoteResult = remote.getSensorAlerts()
            remoteResult.apply {
                onSuccess { alerts ->
                    local.saveAllAlerts(alerts)
                }
            }
        } else {
            localResult
        }
    }
} 