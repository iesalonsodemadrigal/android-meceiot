package edu.iesam.meceiot.features.alerts.data

import edu.iesam.meceiot.features.alerts.data.local.SensorMockLocalDataSource
import edu.iesam.meceiot.features.alerts.domain.Sensor
import edu.iesam.meceiot.features.alerts.domain.SensorRepository

class SensorDataRepository(private val sensorMockLocalDataSource: SensorMockLocalDataSource) :
    SensorRepository {
    override fun getSensors(): Result<List<Sensor>> {
        return sensorMockLocalDataSource.getSensor()
    }
}