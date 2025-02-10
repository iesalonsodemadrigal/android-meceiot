package edu.iesam.meceiot.features.alerts.data.remote.mappers

import android.util.Log
import edu.iesam.meceiot.features.alerts.data.remote.grafanamodel.QuerySensorResponseModel
import edu.iesam.meceiot.features.alerts.domain.Sensor
import edu.iesam.meceiot.features.alerts.domain.TypeSensor

fun QuerySensorResponseModel.toDomain(refId: String): List<Sensor> {
    val result = results.values.firstOrNull()
    val frame = result?.frames?.firstOrNull()


    // Depurar el resultado con un Log
    Log.d("@dev", "Resultado para $refId: $result")
    // Log para ver los detalles del frame
    Log.d("@dev", "Frame para $refId: $frame")

    // Obtener el último valor de la lista de datos del frame
    val data = frame?.data?.getLastValue()

    // Log para ver el valor obtenido
    Log.d("@dev", "Valor obtenido para $refId: $data")


    return data?.let {
        listOf(
            Sensor(
                id = refId,
                name = refId,
                type = TypeSensor.fromType(refId),
                value = it.toString()
            )
        )
    } ?: emptyList()
}