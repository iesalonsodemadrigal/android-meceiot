package edu.iesam.meceiot.features.sensorpanels.data.local

import edu.iesam.meceiot.features.sensorpanels.data.local.db.PanelDao
import edu.iesam.meceiot.features.sensorpanels.data.local.db.SensorDao
import edu.iesam.meceiot.features.sensorpanels.data.local.db.toDomain
import edu.iesam.meceiot.features.sensorpanels.data.local.db.toEntity
import edu.iesam.meceiot.features.sensorpanels.domain.Panel
import edu.iesam.meceiot.features.sensorpanels.domain.Sensor
import org.koin.core.annotation.Single
import java.util.Date

@Single
class SensorPanelDbLocalDataSource(
    private val panelDao: PanelDao,
    private val sensorDao: SensorDao
) {
    private val cache = 60000

    suspend fun getAllPanels(): List<Panel> {
        val panels = panelDao.getPanels()
        return if (panels.isEmpty() || getDate().time - panels.first().date.time > cache) {
            emptyList()
        } else {
            panels.map { panelEntity ->
                panelEntity.toDomain()
            }
        }
    }

    suspend fun saveAllPanels(panels: List<Panel>) {
        val currentDate = getDate()
        panelDao.savePanels(*panels.map { it.toEntity(currentDate) }.toTypedArray())
    }

    suspend fun getAllSensors(): List<Sensor> {
        return sensorDao.getSensors().map { sensorEntity ->
            sensorEntity.toDomain()
        }
    }

    suspend fun saveAllSensors(sensors: List<Sensor>) {
        sensorDao.saveSensors(*sensors.map { it.toEntity() }.toTypedArray())
    }

    private fun getDate(): Date {
        return Date()
    }
}