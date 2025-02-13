package edu.iesam.meceiot.features.alerts.data.remote.mappers

import edu.iesam.meceiot.features.alerts.data.remote.models.SensorQueryResponseModel
import edu.iesam.meceiot.features.alerts.domain.Sensor
import edu.iesam.meceiot.features.alerts.domain.TypeSensor

fun SensorQueryResponseModel.toDomain(refId: String): Sensor {
    val result = results[refId]
    val frame = result?.frames?.firstOrNull()
    val lastValue = frame?.data?.values?.getOrNull(1)?.lastOrNull()

    return Sensor(
        id = refId,
        name = refId,
        type = TypeSensor.fromType(refId),
        value = lastValue.toString()
    )
}
