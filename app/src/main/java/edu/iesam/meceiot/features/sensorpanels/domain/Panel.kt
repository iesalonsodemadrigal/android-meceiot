package edu.iesam.meceiot.features.sensorpanels.domain

import edu.iesam.meceiot.features.sensorpanels.presentation.adapter.ListItem

data class Panel(
    val id: Int,
    val name: String,
    val sensors: List<Sensor>
) : ListItem {
    override fun getType(): Int = ListItem.Type.PANEL.value
}
