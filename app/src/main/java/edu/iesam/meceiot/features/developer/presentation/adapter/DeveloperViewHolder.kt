package edu.iesam.meceiot.features.developer.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.android_meceiot.databinding.ItemDeveloperBinding

import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo

class DeveloperViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private lateinit var binding: ItemDeveloperBinding

    fun bind(developerInfo: DeveloperInfo) {
        binding = ItemDeveloperBinding.bind(view)

        binding.apply {
            developerName.text = developerInfo.full_Name
            developerImage.load(developerInfo.url_Avatar) {
                size(width = 100, height = 100)
                transformations(CircleCropTransformation())
            }



        }


    }
}