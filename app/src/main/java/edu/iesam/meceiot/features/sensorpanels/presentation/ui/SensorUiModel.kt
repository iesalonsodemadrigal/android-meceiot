package edu.iesam.meceiot.features.sensorpanels.presentation.ui

data class SensorUiModel(
    val id: Int,
    val name: String
) : ViewTypeUi {
    override fun getType(): Int = ViewTypeUi.Type.SensorUi.value
}