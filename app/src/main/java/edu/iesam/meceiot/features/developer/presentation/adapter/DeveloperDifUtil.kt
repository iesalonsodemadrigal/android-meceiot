package edu.iesam.meceiot.features.developer.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo

class DeveloperDifUtil : DiffUtil.ItemCallback<DeveloperInfo>() {
    override fun areItemsTheSame(oldItem: DeveloperInfo, newItem: DeveloperInfo): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: DeveloperInfo, newItem: DeveloperInfo): Boolean {
        return oldItem == newItem
    }
}