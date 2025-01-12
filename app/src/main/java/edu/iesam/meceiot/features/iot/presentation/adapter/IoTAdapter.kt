package edu.iesam.meceiot.features.iot.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import edu.iesam.meceiot.R
import edu.iesam.meceiot.features.iot.domain.IoT

class IoTAdapter : ListAdapter<IoT, IoTViewHolder>(IoTDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IoTViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_iot, parent, false)
        return IoTViewHolder(view)
    }

    override fun onBindViewHolder(holder: IoTViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}