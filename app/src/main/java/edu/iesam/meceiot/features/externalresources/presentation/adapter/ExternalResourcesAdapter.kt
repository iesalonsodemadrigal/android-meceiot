package edu.iesam.meceiot.features.externalresources.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import edu.iesam.meceiot.features.externalresources.domain.ExternalResources
import edu.iesam.meceiot.features.externalresources.presentation.ExternalResourcesDiffUtil

class ExternalResourcesAdapter: ListAdapter<ExternalResources, ExternalResourcesViewHolder>(
    ExternalResourcesDiffUtil()
) {
    lateinit var onClick: (resourceId: String) -> Unit

    fun setEvent(onClick: (String) -> Unit) {
        this.onClick = { resourceId -> onClick(resourceId) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResourceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_resources, parent, false)
        return ResourceViewHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: ResourceViewHolder, position: Int) {
        holder.bind(currentList[position], onClick)
    }
}