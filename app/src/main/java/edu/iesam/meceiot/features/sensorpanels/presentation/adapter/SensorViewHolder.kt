package edu.iesam.meceiot.features.sensorpanels.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import edu.iesam.meceiot.R
import edu.iesam.meceiot.databinding.ItemSensorBinding
import edu.iesam.meceiot.features.sensorpanels.domain.Sensor

class SensorViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private val itemSensor = ItemSensorBinding.bind(view)

    fun bind(result: Sensor, onClickListener: (String) -> Unit) {
        itemSensor.apply {
            sensorName.text = result.name
            when (result.name) {
                "Temperature" -> sensorIcon.setImageResource(R.drawable.ic_thermostat)
                "Humidity" -> sensorIcon.setImageResource(R.drawable.ic_humidity_indoor_24px)
                "CO2" -> sensorIcon.setImageResource(R.drawable.ic_co2_24px)
                "Light" -> sensorIcon.setImageResource(R.drawable.ic_light_24px)
                "Motion" -> sensorIcon.setImageResource(R.drawable.ic_motion_24px)
                else -> sensorIcon.setImageResource(R.drawable.ic_generic_sensors_24px)
            }
            view.setOnClickListener {
                onClickListener(result.id.toString())
            }
        }
    }
}