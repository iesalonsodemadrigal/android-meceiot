package edu.iesam.meceiot.features.pantallasensor.data.local.db

import edu.iesam.meceiot.core.domain.ErrorApp
import edu.iesam.meceiot.features.externalresources.data.local.db.TIME_EXTERNAL_RESOURCES
import edu.iesam.meceiot.features.pantallasensor.domain.Sensor
import org.koin.core.annotation.Single

const val TIME_SENSOR = 60000L

@Single
class SensorDbDataSource(private val sensorDao: SensorDao) {

    suspend fun getAll(): Result<List<Sensor>> {
        val sensorEntities = sensorDao.getAll()
        val validEntity = sensorEntities.filter { entity ->
            val timeDifference = System.currentTimeMillis() - entity.date.time
            timeDifference <= TIME_EXTERNAL_RESOURCES
        }
        return if (validEntity.isEmpty()) {
            Result.failure(ErrorApp.DataExpiredError)
        } else {
            Result.success(sensorEntities.map { it.toDomain() })
        }
    }

    suspend fun saveAll(sensors: List<Sensor>) {
        val sensorEntities = sensors.map { it.toEntity() }
        sensorDao.insertAll(*sensorEntities.toTypedArray())
    }

    suspend fun save(sensor: Sensor) {
        val sensorEntity = sensor.toEntity()
        sensorDao.insert(sensorEntity)
    }

    suspend fun getById(id: Int): Sensor {
        return sensorDao.getById(id).toDomain()
    }

}
