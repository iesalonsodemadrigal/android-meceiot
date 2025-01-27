package edu.iesam.meceiot.features.pantallasensor.data.local.db

import edu.iesam.meceiot.core.domain.ErrorApp
import edu.iesam.meceiot.features.externalresources.data.local.db.TIME_EXTERNAL_RESOURCES
import edu.iesam.meceiot.features.pantallasensor.domain.Sensor
import org.koin.core.annotation.Single

const val TIME_SENSOR = 60000L

@Single
class SensorDbDataSource(private val sensorDao: GraphSensorDao) {

    fun getAll(): Result<List<Sensor>> {
        val sensorEntities = sensorDao.getAll()
        val validEntities = sensorEntities.filter { entity ->
            val timeDifference = System.currentTimeMillis() - entity.date.time
            timeDifference <= TIME_EXTERNAL_RESOURCES
        }
        return if (validEntities.isEmpty()) {
            Result.failure(ErrorApp.DataExpiredError)
        } else {
            Result.success(validEntities.mapNotNull { it.toDomain() })
        }
    }

    fun saveAll(sensors: List<Sensor>) {
        val sensorEntities = sensors.map { it.toEntity() }
        sensorDao.insertAll(*sensorEntities.toTypedArray())
    }

    fun save(sensor: Sensor) {
        val sensorEntity = sensor.toEntity()
        sensorDao.insert(sensorEntity)
    }

    fun getById(id: Int): Result<Sensor> {
        val sensorEntity = sensorDao.getById(id)
        return if (sensorEntity != null) {
            Result.success(sensorEntity.toDomain())
        } else {
            Result.failure(ErrorApp.DataErrorApp)
        }
    }

}
