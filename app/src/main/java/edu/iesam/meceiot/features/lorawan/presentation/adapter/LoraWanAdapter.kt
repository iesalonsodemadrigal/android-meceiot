package edu.iesam.meceiot.features.lorawan.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import edu.iesam.meceiot.R
import edu.iesam.meceiot.features.lorawan.domain.LoraWanInfo

class LoraWanAdapter : ListAdapter<LoraWanInfo, LoraWanViewHolder>(LoraWanDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoraWanViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_lorawan_info_item, parent, false)
        return LoraWanViewHolder(view)
    }

    override fun onBindViewHolder(holder: LoraWanViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}