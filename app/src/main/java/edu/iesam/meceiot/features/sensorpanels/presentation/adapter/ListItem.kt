package edu.iesam.meceiot.features.sensorpanels.presentation.adapter

interface ListItem {
    enum class Type(val value: Int) {
        PANEL(0),
        SENSOR(1)
    }

    fun getType(): Int
}