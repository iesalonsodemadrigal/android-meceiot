package edu.iesam.meceiot.features.externalresources.presentation.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import edu.iesam.meceiot.features.externalresources.domain.ExternalResources

class ExternalResourcesViewHolder(private val view: View, private val context: Context) : RecyclerView.ViewHolder(view) {

        fun bind(resource: ExternalResources, onClick: (String) -> Unit) {
            view.setOnClickListener { onClick(resource.resourceName) }
        }
}