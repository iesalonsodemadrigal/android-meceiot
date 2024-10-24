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
        val url = "https://alfaiot.com/wp-content/uploads/2022/11/LoRaWAN_Logo.svg_.png"
        binding.apply {
            titleInfo1.text = loraWanInfo.title
            imageInfo1.loadUrl(url)
            description1.text = loraWanInfo.description
        }
    }
}