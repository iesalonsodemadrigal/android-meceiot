package edu.iesam.meceiot.features.iot.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import edu.iesam.meceiot.features.iot.domain.IoT

class IoTDiffUtil : DiffUtil.ItemCallback<IoT>() {
    override fun areItemsTheSame(oldItem: IoT, newItem: IoT): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: IoT, newItem: IoT): Boolean {
        return oldItem == newItem
    }
}