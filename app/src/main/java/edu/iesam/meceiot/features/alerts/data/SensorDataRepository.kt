package edu.iesam.meceiot.features.alerts.data

import edu.iesam.meceiot.features.alerts.data.local.mock.SensorMockLocalDataSource
import edu.iesam.meceiot.features.alerts.data.remote.firebase.SensorFirebaseRemoteDataSource
import edu.iesam.meceiot.features.alerts.domain.Sensor
import edu.iesam.meceiot.features.alerts.domain.SensorRepository
import org.koin.core.annotation.Single

@Single
class SensorDataRepository(
    private val sensorMockLocalDataSource: SensorMockLocalDataSource,
    private val sensorFirebaseRemoteDataSource: SensorFirebaseRemoteDataSource
) :
    SensorRepository {
    override suspend fun getSensors(): Result<List<Sensor>> {
        val remoteData = sensorFirebaseRemoteDataSource.getSensors()
        return if(remoteData.isFailure){
            sensorMockLocalDataSource.getSensors()
        } else{
            remoteData
        }
    }
}