package edu.iesam.meceiot.features.pantallasensor.data.local.db

import edu.iesam.meceiot.features.pantallasensor.domain.Sensor
import java.util.Date

fun Sensor.toEntity(): SensorEntity =
    SensorEntity(
        id = this.id,
        nombre = this.nombre,
        nombrePanel = this.nombrePanel,
        dataType = this.dataType,
        valoresX = this.valoresX,
        valoresY = this.valoresY,
        date = Date()
    )

fun SensorEntity?.toDomain(): Sensor? {
    return this?.let {
        Sensor(
            id = it.id,
            nombre = it.nombre,
            nombrePanel = it.nombrePanel,
            dataType = it.dataType,
            valoresX = it.valoresX,
            valoresY = it.valoresY,
        )
    }
}
