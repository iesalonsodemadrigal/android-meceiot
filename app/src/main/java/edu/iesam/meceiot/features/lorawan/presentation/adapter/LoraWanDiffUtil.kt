package edu.iesam.meceiot.features.lorawan.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import edu.iesam.meceiot.features.lorawan.domain.LoraWanInfo

class LoraWanDiffUtil : DiffUtil.ItemCallback<LoraWanInfo>() {
    override fun areItemsTheSame(oldItem: LoraWanInfo, newItem: LoraWanInfo): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: LoraWanInfo, newItem: LoraWanInfo): Boolean {
        return oldItem == newItem
    }

}