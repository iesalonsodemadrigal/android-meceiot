package edu.iesam.meceiot.features.pantallasensor.data.local.db

import edu.iesam.meceiot.features.pantallasensor.domain.Sensor
import java.util.Date

fun Sensor.toEntity(): GraphSensorEntity =
    GraphSensorEntity(
        id = this.id,
        nombre = this.name,
        nombrePanel = this.panelName,
        dataType = this.dataType,
        valoresX = this.xValues,
        valoresY = this.yValues,
        maxValue = this.maxValue,
        minValue = this.minValue,
        avgValue = this.avgValue,
        modeValue = this.modeValue,
        date = Date()
    )

fun GraphSensorEntity.toDomain(): Sensor {
    return Sensor(
        id = this.id,
        name = this.nombre,
        panelName = this.nombrePanel,
        dataType = this.dataType,
        xValues = this.valoresX,
        yValues = this.valoresY,
        maxValue = this.maxValue,
        minValue = this.minValue,
        avgValue = this.avgValue,
        modeValue = this.modeValue
        )
}
