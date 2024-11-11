package edu.iesam.meceiot.features.externalresources.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.android_meceiot.databinding.ItemExternalResourcesBinding
import edu.iesam.meceiot.features.externalresources.domain.ExternalResources

class ExternalResourcesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val itemExternalResources: ItemExternalResourcesBinding =
        ItemExternalResourcesBinding.bind(view)

        fun bind(externalResources: ExternalResources, onClick: (String) -> Unit) {
            itemExternalResources.apply {
                ExternalResourcesItemImage.load(externalResources.resourceImage) {}
                ExternalResourcesItemName.text = externalResources.resourceName
                ExternalResourcesItemDescription.text = externalResources.resourceDescription
                view.setOnClickListener {
                    onClick(externalResources.resourceUrl)
                }
            }
        }
}