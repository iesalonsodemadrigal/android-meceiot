package edu.iesam.meceiot.features.lorawan.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.android_meceiot.databinding.ViewLorawanInfoItemBinding
import edu.iesam.meceiot.features.lorawan.domain.LoraWanInfo

class LoraWanViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private lateinit var binding: ViewLorawanInfoItemBinding

    fun bind(loraWanInfo: LoraWanInfo) {
        binding = ViewLorawanInfoItemBinding.bind(view)
        binding.apply {
            titleInfo.text = loraWanInfo.title
            imageInfo.load(loraWanInfo.image) {
                size(width = 1150, height = 800)
                transformations(RoundedCornersTransformation(60f))
            }
            description.text = loraWanInfo.description
        }
    }
}