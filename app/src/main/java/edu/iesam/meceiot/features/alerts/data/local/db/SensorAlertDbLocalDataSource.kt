package edu.iesam.meceiot.features.alerts.data.local.db

import edu.iesam.meceiot.features.alerts.domain.Panel
import edu.iesam.meceiot.features.alerts.domain.Sensor
import org.koin.core.annotation.Single

@Single
class SensorAlertDbLocalDataSource(
    private val panelAlertDao: PanelAlertDao,
    private val sensorAlertDao: SensorAlertDao
) {

    suspend fun getAllPanels(): Result<List<Panel>> {
        val panelsEntities = panelAlertDao.getPanels()
        return Result.success(panelsEntities.map { it.toDomain() })
    }

    suspend fun saveAllPanels(panels: List<Panel>) {
        val panelEntities = panels.map { it.toEntity() }
        panelAlertDao.savePanels(*panelEntities.toTypedArray())
    }

    suspend fun getAllSensors(): Result<List<Sensor>> {
        val sensorsEntities = sensorAlertDao.getSensors()
        return Result.success(sensorsEntities.map { it.toDomain() })
    }

    suspend fun saveAllSensors(sensors: List<Sensor>) {
        val sensorsEntities = sensors.map { it.toEntity() }
        sensorAlertDao.saveSensors(*sensorsEntities.toTypedArray())
    }

}