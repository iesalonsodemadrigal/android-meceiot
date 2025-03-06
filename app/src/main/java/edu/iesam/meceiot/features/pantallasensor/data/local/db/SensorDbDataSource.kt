package edu.iesam.meceiot.features.pantallasensor.data.local.db

import edu.iesam.meceiot.core.domain.ErrorApp
import edu.iesam.meceiot.features.pantallasensor.domain.GraphSensor
import org.koin.core.annotation.Single

const val TIME_SENSOR = 60000L

@Single
class SensorDbDataSource(private val sensorDao: GraphSensorDao) {

    fun getAll(): Result<List<GraphSensor>> {
        val sensorEntities = sensorDao.getAll()
        val validEntities = sensorEntities.filter { entity ->
            val timeDifference = System.currentTimeMillis() - entity.date.time
            timeDifference <= TIME_SENSOR
        }
        return if (validEntities.isEmpty()) {
            Result.failure(ErrorApp.DataExpiredError)
        } else {
            Result.success(validEntities.mapNotNull { it.toDomain() })
        }
    }

    fun saveAll(graphSensors: List<GraphSensor>) {
        val sensorEntities = graphSensors.map { it.toEntity() }
        sensorDao.insertAll(*sensorEntities.toTypedArray())
    }

    fun save(graphSensor: GraphSensor) {
        val sensorEntity = graphSensor.toEntity()
        sensorDao.insert(sensorEntity)
    }

    fun getById(id: Int): Result<GraphSensor> {
        val sensorEntity = sensorDao.getById(id)
        return if (sensorEntity != null) {
            Result.success(sensorEntity.toDomain())
        } else {
            Result.failure(ErrorApp.DataErrorApp)
        }
    }

}
