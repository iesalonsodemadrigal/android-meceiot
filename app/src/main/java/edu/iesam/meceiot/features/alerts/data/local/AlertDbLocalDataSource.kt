package edu.iesam.meceiot.features.alerts.data.local

import edu.iesam.meceiot.core.domain.ErrorApp
import edu.iesam.meceiot.features.alerts.data.local.db.AlertDao
import edu.iesam.meceiot.features.alerts.data.local.db.toDomain
import edu.iesam.meceiot.features.alerts.data.local.db.toEntity
import edu.iesam.meceiot.features.alerts.domain.Alert
import org.koin.core.annotation.Single

@Single
class AlertDbLocalDataSource(
    private val alertDao: AlertDao
) {
    private val cache = 15000L //15s

    suspend fun getAllAlerts(): Result<List<Alert>> {
        val alerts = alertDao.getAlerts()
        return if (alerts.isEmpty() || System.currentTimeMillis() - alerts.first().date > cache) {
            Result.failure(ErrorApp.DataExpiredError)
        } else {
            Result.success(
                alerts.map { alertEntity ->
                    alertEntity.toDomain()
                }
            )
        }
    }

    suspend fun saveAllAlerts(alerts: List<Alert>) {
        val currentTime = System.currentTimeMillis()
        alertDao.saveAlerts(*alerts.map { it.toEntity(currentTime) }.toTypedArray())
    }
} 