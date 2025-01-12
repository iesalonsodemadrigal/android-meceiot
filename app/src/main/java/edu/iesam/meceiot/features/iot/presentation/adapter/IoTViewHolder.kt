package edu.iesam.meceiot.features.iot.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import edu.iesam.meceiot.databinding.ItemIotBinding
import edu.iesam.meceiot.features.iot.domain.IoT

class IoTViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private lateinit var binding: ItemIotBinding

    fun bind(iot: IoT) {
        binding = ItemIotBinding.bind(view)
        binding.apply {
            itemTitle.text = iot.title
            ItemImage.load(iot.image)
            itemDescription.text = iot.description
        }
    }
}