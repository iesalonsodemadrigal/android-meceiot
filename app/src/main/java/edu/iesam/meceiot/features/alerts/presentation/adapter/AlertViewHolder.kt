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
            // Mostrar el nombre de la zona
            titlePanel.text = zone.name

            // Aquí tomamos solo los sensores filtrados
            val filteredSensors = zone.sensors.filter { sensor ->
                sensor.type == "mov" && sensor.value != "0" && sensor.value >= "1"
            }

            // Si hay sensores filtrados, mostramos su descripción
            if (filteredSensors.isNotEmpty()) {
                val sensor = filteredSensors.joinToString(separator = "\n") { sensor ->
                    "Descripción: ${sensor.description}"

                }
                descriptionSensor.text = sensor
            }
        }
    }
}