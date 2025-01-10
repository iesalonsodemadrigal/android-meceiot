package edu.iesam.meceiot.features.pantallasensor.data

import edu.iesam.meceiot.features.pantallasensor.data.local.SensorMockLocalDataSource
import edu.iesam.meceiot.features.pantallasensor.domain.Sensor
import edu.iesam.meceiot.features.pantallasensor.domain.SensorRepository
import org.koin.core.annotation.Single

@Single
class SensorDataRepository(private val sensorLocalDataSource: SensorMockLocalDataSource) :
    SensorRepository {
    override suspend fun getSensorData(): Result<List<Sensor>> {
        val localSensorData = sensorLocalDataSource.getSensorData()
        return if (localSensorData.isFailure || localSensorData.getOrNull().isNullOrEmpty()) {
            Result.failure(Exception("No data available"))
        } else {
            Result.success(localSensorData)
        }
    }
}