package edu.iesam.meceiot.features.pantallasensor.data.local.db

import edu.iesam.meceiot.features.pantallasensor.domain.GraphSensor
import java.util.Date

fun GraphSensor.toEntity(fromDate: Long, toDate: Long): GraphSensorEntity =
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
        fromDate = fromDate,
        toDate = toDate,
        date = Date()
    )

fun GraphSensorEntity.toDomain(): GraphSensor {
    return GraphSensor(
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
