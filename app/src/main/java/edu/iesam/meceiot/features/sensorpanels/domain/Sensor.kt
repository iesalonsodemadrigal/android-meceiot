package edu.iesam.meceiot.features.sensorpanels.domain

data class Sensor(
    val id: Int,
    val name: String
) : PanelUiModel {
    override fun getType(): Int = PanelUiModel.Type.SensorUi.value
}