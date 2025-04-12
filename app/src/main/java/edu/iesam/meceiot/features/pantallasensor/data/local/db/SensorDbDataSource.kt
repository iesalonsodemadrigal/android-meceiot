package edu.iesam.meceiot.features.pantallasensor.data.local.db

import android.util.Log
import edu.iesam.meceiot.core.domain.ErrorApp
import edu.iesam.meceiot.features.pantallasensor.domain.GraphSensor
import org.koin.core.annotation.Single

// Ampliamos el TTL a 8 horas (en milisegundos)
const val TIME_SENSOR = 8 * 60 * 60 * 1000L  // 8 horas

@Single
class SensorDbDataSource(private val sensorDao: GraphSensorDao) {

    fun save(graphSensor: GraphSensor) {
        try {
            val sensorEntity = graphSensor.toEntity()
            Log.d(
                "SensorDbDataSource",
                "Saving sensor: id=${sensorEntity.id}, name=${sensorEntity.nombre}, values size=${sensorEntity.valoresX.size}"
            )
            sensorDao.insert(sensorEntity)

            // Verificamos que se guardó correctamente
            val savedEntity = sensorDao.getById(sensorEntity.id)
            if (savedEntity != null) {
                Log.d("SensorDbDataSource", "Sensor saved successfully: id=${sensorEntity.id}")
            } else {
                Log.e("SensorDbDataSource", "Failed to save sensor: id=${sensorEntity.id}")
            }
        } catch (e: Exception) {
            Log.e("SensorDbDataSource", "Error saving sensor: ${e.message}", e)
        }
    }

    fun getById(id: Int): Result<GraphSensor> {
        try {
            Log.d("SensorDbDataSource", "Looking for sensor with id=$id")
            val sensorEntity = sensorDao.getById(id)

            return if (sensorEntity != null) {
                val currentTime = System.currentTimeMillis()
                val entityTime = sensorEntity.date.time
                val timeDifference = currentTime - entityTime

                if (timeDifference <= TIME_SENSOR) {
                    Log.d(
                        "SensorDbDataSource",
                        "Sensor found and valid: id=$id, age=${timeDifference / 1000}s, values size=${sensorEntity.valoresX.size}"
                    )
                    Result.success(sensorEntity.toDomain())
                } else {
                    Log.d(
                        "SensorDbDataSource",
                        "Sensor found but expired: id=$id, age=${timeDifference / 1000}s"
                    )
                    Result.failure(ErrorApp.DataExpiredError)
                }
            } else {
                // Si no se encuentra, imprimimos los IDs disponibles para diagnóstico
                val allSensors = sensorDao.getAll()
                val availableIds = allSensors.map { it.id }
                Log.d(
                    "SensorDbDataSource",
                    "Sensor not found: id=$id. Available IDs: $availableIds"
                )
                Result.failure(ErrorApp.DataErrorApp)
            }
        } catch (e: Exception) {
            Log.e("SensorDbDataSource", "Error getting sensor: ${e.message}", e)
            return Result.failure(e)
        }
    }
}
