package edu.iesam.meceiot.features.pantallasensor.data.remote

import edu.iesam.meceiot.features.pantallasensor.domain.GraphSensor

fun SensorGraphApiModel.toModel(): GraphSensor {
    return GraphSensor(
        id = id,
        name = name,
        panelName = panelName,
        dataType = dataType,
        xValues = xValues,
        yValues = yValues,
        maxValue = maxValue,
        minValue = minValue,
        avgValue = avgValue,
        modeValue = modeValue
    )
}