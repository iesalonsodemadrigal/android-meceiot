package edu.iesam.meceiot.features.alerts.data

import edu.iesam.meceiot.features.alerts.data.remote.SensorGrafanaRemoteDataSource
import edu.iesam.meceiot.features.alerts.domain.Sensor
import edu.iesam.meceiot.features.alerts.domain.SensorAlertRepository
import org.koin.core.annotation.Single

@Single
class SensorAlertDataRepository(
    private val sensorGrafanaRemoteDataSource: SensorGrafanaRemoteDataSource
) :
    SensorAlertRepository {
    override suspend fun getSensors(): Result<List<Sensor>> {
        return sensorGrafanaRemoteDataSource.getSensors()
    }
}