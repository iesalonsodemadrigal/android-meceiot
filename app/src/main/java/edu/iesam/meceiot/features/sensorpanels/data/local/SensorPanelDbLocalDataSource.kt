package edu.iesam.meceiot.features.sensorpanels.data.local

import edu.iesam.meceiot.core.domain.ErrorApp
import edu.iesam.meceiot.features.sensorpanels.data.local.db.PanelDao
import edu.iesam.meceiot.features.sensorpanels.data.local.db.toDomain
import edu.iesam.meceiot.features.sensorpanels.data.local.db.toEntity
import edu.iesam.meceiot.features.sensorpanels.domain.Panel
import org.koin.core.annotation.Single

@Single
class SensorPanelDbLocalDataSource(
    private val panelDao: PanelDao
) {
    private val cache = 900000L // 15 minutes

    suspend fun getAllPanels(): Result<List<Panel>> {
        val panels = panelDao.getPanels()
        return if (panels.isEmpty() || System.currentTimeMillis() - panels.first().date > cache) {
            Result.failure(ErrorApp.DataExpiredError)
        } else {
            Result.success(
                panels.map { panelEntity ->
                    panelEntity.toDomain()
                }
            )
        }
    }

    suspend fun saveAllPanels(panels: List<Panel>) {
        val currentTime = System.currentTimeMillis()
        panelDao.savePanels(*panels.map { it.toEntity(currentTime) }.toTypedArray())
    }
}