package edu.iesam.meceiot.features.sensorpanels.domain

import edu.iesam.meceiot.features.sensorpanels.presentation.adapter.ListItem

data class Sensor(
    val id: Int,
    val name: String
) : ListItem {
    override fun getType(): Int = ListItem.Type.SENSOR.value
}