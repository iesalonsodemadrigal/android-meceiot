package edu.iesam.meceiot.features.sensorpanels.data

import edu.iesam.meceiot.features.grafana.data.remote.GrafanaRemoteDataSource
import edu.iesam.meceiot.features.sensorpanels.data.local.SensorPanelDbLocalDataSource
import edu.iesam.meceiot.features.sensorpanels.domain.Panel
import edu.iesam.meceiot.features.sensorpanels.domain.SensorPanelRepository
import org.koin.core.annotation.Single

@Single
class SensorPanelsDataRepository(
    private val remote: GrafanaRemoteDataSource,
    private val local: SensorPanelDbLocalDataSource
) : SensorPanelRepository {
    override suspend fun getSensorPanels(): Result<List<Panel>> {
        val sensorPanelsFromLocal = local.getAllPanels()
        return if (sensorPanelsFromLocal.isFailure) {
            val sensorPanelsFromRemote = remote.getSensorPanels()
            sensorPanelsFromRemote.apply {
                onSuccess {
                    local.saveAllPanels(it)
                }
            }
        } else {
            return sensorPanelsFromLocal
        }
    }
}