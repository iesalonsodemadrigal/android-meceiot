package edu.iesam.meceiot.features.pantallasensor.data.remote

import com.google.gson.annotations.SerializedName

data class SensorGraphApiModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("panel_name") val panelName: String,
    @SerializedName("data_type") val dataType: String,
    @SerializedName("x_values") val xValues: List<Long>,
    @SerializedName("y_values") val yValues: List<Int>,
    @SerializedName("max_value") val maxValue: String,
    @SerializedName("min_value") val minValue: String,
    @SerializedName("avg_value") val avgValue: String,
    @SerializedName("mode_value") val modeValue: String
)