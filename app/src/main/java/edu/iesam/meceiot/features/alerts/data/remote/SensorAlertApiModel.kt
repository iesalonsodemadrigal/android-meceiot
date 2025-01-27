package edu.iesam.meceiot.features.alerts.data.remote

import com.google.gson.annotations.SerializedName
import edu.iesam.meceiot.features.alerts.domain.TypeSensor

data class PanelApiModel(
    @SerializedName("id") val idZone: String,
    @SerializedName("name") val nameZone: String,
    @SerializedName("sensors") val sensors: List<SensorApiModel>
)

data class SensorApiModel(
    @SerializedName("id") val idSensor: String,
    @SerializedName("name") val nameSensor: String,
    @SerializedName("description") val description: String,
    @SerializedName("type") val type: TypeSensorApiModel,
    @SerializedName("value") val value: String
)

data class TypeSensorApiModel(
    @SerializedName("type") val type: String
) {


    fun toTypeSensor(): TypeSensor {
        return TypeSensor.fromType(type)
    }
}