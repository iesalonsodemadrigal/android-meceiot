package edu.iesam.meceiot.features.sensorpanels.presentation

import androidx.recyclerview.widget.DiffUtil
import edu.iesam.meceiot.features.sensorpanels.domain.Panel

class SensorPanelsDiffUtil : DiffUtil.ItemCallback<Panel>() {
    override fun areItemsTheSame(oldItem: Panel, newItem: Panel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Panel, newItem: Panel): Boolean {
        return oldItem == newItem
    }
}