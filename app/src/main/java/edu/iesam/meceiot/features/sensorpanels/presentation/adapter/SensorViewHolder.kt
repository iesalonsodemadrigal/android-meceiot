package edu.iesam.meceiot.features.sensorpanels.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import edu.iesam.meceiot.R
import edu.iesam.meceiot.databinding.ItemSensorBinding
import edu.iesam.meceiot.features.sensorpanels.presentation.ui.SensorUiModel

class SensorViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private val itemSensor = ItemSensorBinding.bind(view)

    fun bind(result: SensorUiModel, onClickListener: (String) -> Unit) {
        itemSensor.apply {
            sensorName.text = result.name
            when (result.name) {
                "Temperature" -> sensorIcon.setImageResource(R.drawable.ic_thermostat)
                "Humidity" -> sensorIcon.setImageResource(R.drawable.ic_humidity)
                "CO2" -> sensorIcon.setImageResource(R.drawable.ic_co2)
                "Light" -> sensorIcon.setImageResource(R.drawable.ic_light)
                "Motion" -> sensorIcon.setImageResource(R.drawable.ic_motion)
                "Battery" -> sensorIcon.setImageResource(R.drawable.ic_battery)
                "Sound" -> sensorIcon.setImageResource(R.drawable.ic_sound)
                else -> sensorIcon.setImageResource(R.drawable.ic_generic_sensor)
            }
            view.setOnClickListener {
                onClickListener(result.id.toString())
            }
        }
    }
}