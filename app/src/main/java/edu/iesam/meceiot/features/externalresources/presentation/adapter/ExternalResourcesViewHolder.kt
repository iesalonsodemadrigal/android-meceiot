package edu.iesam.meceiot.features.externalresources.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import edu.iesam.meceiot.databinding.ItemExternalResourcesBinding

import edu.iesam.meceiot.features.externalresources.domain.ExternalResources

class ExternalResourcesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val itemExternalResources: ItemExternalResourcesBinding =
        ItemExternalResourcesBinding.bind(view)

    fun bind(externalResources: ExternalResources, onClick: (String) -> Unit) {
        itemExternalResources.apply {
            externalResourcesItemImage.load(externalResources.image) {}
            externalResourcesItemName.text = externalResources.author
            externalResourcesItemDescription.text = externalResources.description
        }
        view.setOnClickListener { onClick(externalResources.url) }

    }
}