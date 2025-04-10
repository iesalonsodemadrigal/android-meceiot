package edu.iesam.meceiot.features.sensorpanels.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import edu.iesam.meceiot.R
import edu.iesam.meceiot.databinding.ItemSensorBinding
import edu.iesam.meceiot.features.sensorpanels.presentation.ui.SensorUiModel

class SensorViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private val itemSensor = ItemSensorBinding.bind(view)

    fun bind(result: SensorUiModel, onClickListener: (String, String, String, Int) -> Unit) {
        itemSensor.apply {
            sensorName.text = result.name
            when (result.name) {
                "Temp [ºC]" -> sensorIcon.setImageResource(R.drawable.ic_thermostat)
                "Hum [%]" -> sensorIcon.setImageResource(R.drawable.ic_humidity)
                "CO2 [ppm]" -> sensorIcon.setImageResource(R.drawable.ic_co2)
                "Luz [lux]" -> sensorIcon.setImageResource(R.drawable.ic_light)
                "Movimiento" -> sensorIcon.setImageResource(R.drawable.ic_motion)
                "Bat [mV]" -> sensorIcon.setImageResource(R.drawable.ic_battery)
                "Sound Avg [dB]" -> sensorIcon.setImageResource(R.drawable.ic_sound)
                "Sound Max [dB]" -> sensorIcon.setImageResource(R.drawable.ic_sound)
                else -> sensorIcon.setImageResource(R.drawable.ic_generic_sensor)
            }
            view.setOnClickListener {
                onClickListener(
                    result.name,
                    result.panelName,
                    result.query,
                    result.id
                )
            }
        }
    }
}