package edu.iesam.meceiot.features.externalresources.presentation.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.transform.CircleCropTransformation
import com.example.android_meceiot.databinding.ItemExternalResourcesBinding
import edu.iesam.meceiot.features.externalresources.domain.ExternalResources

class ExternalResourcesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val binding : ItemExternalResourcesBinding = ItemExternalResourcesBinding.bind(view)

        fun bind(externalResources: ExternalResources, onClick: (String) -> Unit) {
            binding.apply {
                resourceImage.load(externalResources.resourceImage) {
                    transformations(CircleCropTransformation())
                }
                root.setOnClickListener {
                    onClick(externalResources.resourceImage)
                }
            }
        }
}