package edu.iesam.meceiot.features.alerts.data.remote.mappers

import edu.iesam.meceiot.features.alerts.data.remote.models.SensorQueryResponseModel
import edu.iesam.meceiot.features.alerts.domain.Sensor
import edu.iesam.meceiot.features.alerts.domain.TypeSensor

fun SensorQueryResponseModel.toDomain(refId: String): Sensor {
    val result = results[refId]
    val frame = result?.frames?.firstOrNull()
    val lastValue = frame?.data?.values?.getOrNull(1)?.lastOrNull()

    return sensorToDomain(refId, refId, extractTypeFromRefId(refId), lastValue.toString())
}

fun sensorToDomain(refId: String, title: String, type: TypeSensor, lastValue: String): Sensor {
    return Sensor(
        id = refId,
        name = title,
        type = type,
        value = lastValue
    )
}


fun extractTypeFromRefId(refId: String): TypeSensor {
    return when {
        refId.contains("Sound", ignoreCase = true) -> TypeSensor.Sound
        refId.contains("Temp [ºC]", ignoreCase = true) -> TypeSensor.Temperature
        refId.contains("Luz [lux]", ignoreCase = true) -> TypeSensor.Light
        refId.contains("Hum [%]", ignoreCase = true) -> TypeSensor.Humidity
        refId.contains("CO2 [ppm]", ignoreCase = true) -> TypeSensor.Co2
        refId.contains("Movimiento", ignoreCase = true) -> TypeSensor.Movement
        else -> TypeSensor.UnknownSensor
    }
}
