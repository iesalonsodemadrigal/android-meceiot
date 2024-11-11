package edu.iesam.meceiot.features.externalresources.presentation

import androidx.recyclerview.widget.DiffUtil
import edu.iesam.meceiot.features.externalresources.domain.ExternalResources

class ExternalResourcesDiffUtil : DiffUtil.ItemCallback<ExternalResources>() {
    override fun areItemsTheSame(oldItem: ExternalResources, newItem: ExternalResources): Boolean {
        return oldItem.resourceName == newItem.resourceName
    }

    override fun areContentsTheSame(
        oldItem: ExternalResources,
        newItem: ExternalResources
    ): Boolean {
        return oldItem == newItem
    }
}