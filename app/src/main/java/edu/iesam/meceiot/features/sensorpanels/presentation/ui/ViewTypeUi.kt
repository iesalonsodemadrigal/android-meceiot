package edu.iesam.meceiot.features.sensorpanels.presentation.ui

interface ViewTypeUi {
    enum class Type(val value: Int) {
        PanelUI(0),
        SensorUi(1)
    }
    fun getType(): Int
}