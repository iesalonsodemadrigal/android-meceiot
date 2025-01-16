package edu.iesam.meceiot.features.sensorpanels.data.local

import edu.iesam.meceiot.features.sensorpanels.data.local.db.PanelDao
import edu.iesam.meceiot.features.sensorpanels.data.local.db.SensorDao
import edu.iesam.meceiot.features.sensorpanels.data.local.db.toDomain
import edu.iesam.meceiot.features.sensorpanels.data.local.db.toEntity
import edu.iesam.meceiot.features.sensorpanels.domain.Panel
import edu.iesam.meceiot.features.sensorpanels.domain.Sensor
import org.koin.core.annotation.Single

@Single
class SensorPanelDbLocalDataSource(
    private val panelDao: PanelDao,
    private val sensorDao: SensorDao
) {
    suspend fun getAllPanels(): List<Panel> {
        val panels = panelDao.getPanels()
        //Si se decide TTL se pondrá aquí
        return if (panels.isEmpty()) {
            emptyList()
        } else {
            panels.map { panelEntity ->
                panelEntity.toDomain()
            }
        }
    }

    suspend fun saveAllPanels(panels: List<Panel>) {
        panelDao.savePanels(*panels.map { it.toEntity() }.toTypedArray())
    }

    suspend fun getAllSensors(): List<Sensor> {
        return sensorDao.getSensors().map { sensorEntity ->
            sensorEntity.toDomain()
        }
    }

    suspend fun saveAllSensors(sensors: List<Sensor>) {
        sensorDao.saveSensors(*sensors.map { it.toEntity() }.toTypedArray())
    }
}