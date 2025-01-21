package edu.iesam.meceiot.features.alerts.data

import edu.iesam.meceiot.features.alerts.data.local.mock.SensorMockLocalDataSource
import edu.iesam.meceiot.features.alerts.domain.Sensor
import edu.iesam.meceiot.features.alerts.domain.SensorRepository
import org.koin.core.annotation.Single

@Single
class SensorDataRepository(
    private val sensorMockLocalDataSource: SensorMockLocalDataSource,
    //private val sensorApiRemoteDataSource: SensorApiRemoteDataSource
) :
    SensorRepository {
    override fun getSensors(): Result<List<Sensor>> {
        return sensorMockLocalDataSource.getSensor()
    }
}