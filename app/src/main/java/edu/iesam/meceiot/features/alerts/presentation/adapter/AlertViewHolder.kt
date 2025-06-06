package edu.iesam.meceiot.features.alerts.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import edu.iesam.meceiot.R
import edu.iesam.meceiot.databinding.ItemAlertBinding
import edu.iesam.meceiot.features.alerts.domain.Alert
import edu.iesam.meceiot.features.alerts.domain.TypeSensor

class AlertViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private lateinit var binding: ItemAlertBinding

    fun bind(alert: Alert) {
        binding = ItemAlertBinding.bind(view)
        binding.apply {

            imageItem.setImageResource(
                when (alert.type) {
                    TypeSensor.Co2 -> R.drawable.ic_co2
                    TypeSensor.Temperature -> R.drawable.ic_temperature
                    TypeSensor.Light -> R.drawable.ic_light
                    TypeSensor.Humidity -> R.drawable.ic_humidity
                    TypeSensor.Movement -> R.drawable.ic_motion
                    TypeSensor.Sound -> R.drawable.ic_sound
                    else -> R.drawable.ic_generic_sensor
                }
            )

            titleAlert.setText(
                when (alert.type) {
                    TypeSensor.Co2 -> R.string.alert_co2
                    TypeSensor.Temperature -> R.string.alert_temperature
                    TypeSensor.Light -> R.string.alert_light
                    TypeSensor.Humidity -> R.string.alert_humidity
                    TypeSensor.Movement -> R.string.alert_movement
                    TypeSensor.Sound -> R.string.alert_sound
                    else -> R.string.description_panel
                }
            )
            sensorName.visibility = View.GONE
            sensorLocation.text = alert.location
        }
    }
}
