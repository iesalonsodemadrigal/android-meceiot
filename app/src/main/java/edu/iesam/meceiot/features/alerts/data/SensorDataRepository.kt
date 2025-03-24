package edu.iesam.meceiot.features.alerts.data

import edu.iesam.meceiot.features.alerts.domain.Sensor
import edu.iesam.meceiot.features.alerts.domain.SensorRepository
import edu.iesam.meceiot.features.grafana.data.remote.GrafanaRemoteDataSource
import org.koin.core.annotation.Single

@Single
class SensorDataRepository(
    private val grafanaRemoteDataSource: GrafanaRemoteDataSource
) : SensorRepository {
    override suspend fun getSensorAlerts(): Result<List<Sensor>> {
        return grafanaRemoteDataSource.getSensorAlerts()
    }
}