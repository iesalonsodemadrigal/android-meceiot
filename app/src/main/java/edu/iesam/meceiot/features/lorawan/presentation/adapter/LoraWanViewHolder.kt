package edu.iesam.meceiot.features.lorawan.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.android_meceiot.databinding.ViewLorawanInfoItemBinding
import edu.iesam.meceiot.core.extensions.loadUrl
import edu.iesam.meceiot.features.lorawan.domain.LoraWanInfo

class LoraWanViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private lateinit var binding: ViewLorawanInfoItemBinding

    fun bind(loraWanInfo: LoraWanInfo) {
        binding = ViewLorawanInfoItemBinding.bind(view)
        binding.apply {
            titleInfo1.text = loraWanInfo.title
            imageInfo1.loadUrl(loraWanInfo.image)
            description1.text = loraWanInfo.description
        }
    }
}