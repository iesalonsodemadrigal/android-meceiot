package edu.iesam.meceiot.features.sensorpanels.domain

interface PanelUiModel {
    enum class Type(val value: Int) {
        PanelUI(0),
        SensorUi(1)
    }

    fun getType(): Int
}