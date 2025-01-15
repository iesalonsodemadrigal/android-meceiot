package edu.iesam.meceiot.features.alerts.data

import edu.iesam.meceiot.features.alerts.data.local.SensorMockLocalDataSource
import edu.iesam.meceiot.features.alerts.domain.SensorRepository
import edu.iesam.meceiot.features.alerts.domain.Zone
import org.koin.core.annotation.Single

@Single
class SensorDataRepository(private val sensorMockLocalDataSource: SensorMockLocalDataSource) :
    SensorRepository {
    override fun getSensors(): Result<List<Zone>> {
        return sensorMockLocalDataSource.getSensor()
    }
}