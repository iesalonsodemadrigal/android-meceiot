package edu.iesam.meceiot.features.sensorpanels.presentation.ui

data class PanelUiModel(
    val id: Int,
    val name: String,
    val sensors: List<SensorUiModel>
) : ViewTypeUi {
    override fun getType(): Int = ViewTypeUi.Type.PanelUI.value
}
