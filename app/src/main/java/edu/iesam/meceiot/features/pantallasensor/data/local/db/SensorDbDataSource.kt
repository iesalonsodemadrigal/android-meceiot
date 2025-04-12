package edu.iesam.meceiot.features.pantallasensor.data.local.db

import edu.iesam.meceiot.core.domain.ErrorApp
import edu.iesam.meceiot.features.pantallasensor.domain.GraphSensor
import org.koin.core.annotation.Single

const val TIME_SENSOR = 1000 * 60 * 15 // 5 minutos

@Single
class SensorDbDataSource(private val sensorDao: GraphSensorDao) {

    fun save(graphSensor: GraphSensor, fromDate: Long, toDate: Long) {
        val sensorEntity = graphSensor.toEntity(fromDate, toDate)
        sensorDao.insert(sensorEntity)
    }

    fun getByIdAndDateRange(id: Int, fromDate: Long, toDate: Long): Result<GraphSensor> {
        val sensorData = sensorDao.getById(id)
        return if (sensorData == null || System.currentTimeMillis() - sensorData.date.time > TIME_SENSOR) {
            Result.failure(ErrorApp.DataExpiredError)
        } else if (sensorData.fromDate == fromDate && sensorData.toDate == toDate) {
            Result.failure(ErrorApp.DataExpiredError)
        } else {
            Result.success(sensorData.toDomain())
        }
    }
}
