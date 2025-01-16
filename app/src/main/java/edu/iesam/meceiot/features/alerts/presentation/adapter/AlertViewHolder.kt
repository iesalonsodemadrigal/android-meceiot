package edu.iesam.meceiot.features.alerts.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import edu.iesam.meceiot.databinding.ItemAlertBinding
import edu.iesam.meceiot.features.alerts.domain.Zone

class AlertViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private lateinit var binding: ItemAlertBinding

    fun bind(zone: Zone) {
        binding = ItemAlertBinding.bind(view)
        binding.apply {
            titlePanel.text = zone.name
            descriptionSensor.text = zone.sensors.joinToString { it.description }
        }
    }
}