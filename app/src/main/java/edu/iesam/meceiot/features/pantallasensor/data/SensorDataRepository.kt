package edu.iesam.meceiot.features.pantallasensor.data

import edu.iesam.meceiot.features.pantallasensor.data.local.db.SensorDbDataSource
import edu.iesam.meceiot.features.pantallasensor.data.remote.SensorRemoteMockDataSource
import edu.iesam.meceiot.features.pantallasensor.domain.Sensor
import edu.iesam.meceiot.features.pantallasensor.domain.SensorRepository
import org.koin.core.annotation.Single

@Single
class SensorDataRepository(
    private val remoteDataSource: SensorRemoteMockDataSource,
    private val localDataSource: SensorDbDataSource
) : SensorRepository {
    override fun getSensorDataById(id: Int): Sensor {
        val localSensor = localDataSource.getById(id)
        if (localSensor == null) {
            localDataSource.save(remoteDataSource.getSensorData())
            return remoteDataSource.getSensorData()
        } else {
            return localSensor
        }
    }
}
