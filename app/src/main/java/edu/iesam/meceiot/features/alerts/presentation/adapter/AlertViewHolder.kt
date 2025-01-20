package edu.iesam.meceiot.features.alerts.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import edu.iesam.meceiot.R
import edu.iesam.meceiot.databinding.ItemAlertBinding
import edu.iesam.meceiot.features.alerts.domain.Sensor
import edu.iesam.meceiot.features.alerts.domain.TypeSensor

class AlertViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private lateinit var binding: ItemAlertBinding

    fun bind(sensor: Sensor) {
        binding = ItemAlertBinding.bind(view)
        binding.apply {
            descriptionSensor.text = sensor.name
            titleAlert.text = sensor.description


            iconSensor.setImageResource(
                when (sensor.type) {
                    TypeSensor.Co2 -> R.drawable.ic_co2
                    TypeSensor.Temperature -> R.drawable.ic_temperature
                    TypeSensor.Light -> R.drawable.ic_light
                    TypeSensor.Humidity -> R.drawable.ic_humidity
                    TypeSensor.Movement -> R.drawable.ic_motion
                    TypeSensor.Sound -> R.drawable.ic_sound
                    else -> R.drawable.ic_alert_sensor
                }
            )
        }
    }
}