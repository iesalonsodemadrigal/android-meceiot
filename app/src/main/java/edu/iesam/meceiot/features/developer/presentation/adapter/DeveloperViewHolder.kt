package edu.iesam.meceiot.features.developer.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.android_meceiot.databinding.ItemDeveloperBinding
import edu.iesam.meceiot.core.extensions.loadUrl
import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo

class DeveloperViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private lateinit var binding: ItemDeveloperBinding

    fun bind(developerInfo: DeveloperInfo) {
        binding = ItemDeveloperBinding.bind(view)

        binding.apply {
            developerName.text = developerInfo.name
            developerImage.loadUrl(developerInfo.image)
            developerDescription.text = developerInfo.description
        }

    }
}