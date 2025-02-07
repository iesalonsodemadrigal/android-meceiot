package edu.iesam.meceiot.features.pantallasensor.data.remote.firebase

import edu.iesam.meceiot.features.pantallasensor.domain.GraphSensor

fun GraphSensorFirebaseModel.toDomain() = GraphSensor(
    id = this.id,
    name = this.name,
    panelName = this.panelName,
    dataType = this.dataType,
    xValues = this.xValues,
    yValues = this.yValues,
    maxValue = this.maxValue,
    minValue = this.minValue,
    avgValue = this.avgValue,
    modeValue = this.modeValue
)