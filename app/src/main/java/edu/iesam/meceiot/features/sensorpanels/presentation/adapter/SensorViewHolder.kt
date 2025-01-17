package edu.iesam.meceiot.features.sensorpanels.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import edu.iesam.meceiot.databinding.ItemSensorBinding
import edu.iesam.meceiot.features.sensorpanels.domain.Sensor

class SensorViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private val itemSensor = ItemSensorBinding.bind(view)

    fun bind(result: Sensor, onClickListener: (String) -> Unit) {
        itemSensor.apply {
            sensorName.text = result.name
            /*when(sensorName){
                "Temperature" -> sensorIcon.setImageResource(R.drawable.ic_temperature)
                "Humidity" -> sensorIcon.setImageResource(R.drawable.ic_humidity)
                "Light" -> sensorIcon.setImageResource(R.drawable.ic_light)
                "Motion" -> sensorIcon.setImageResource(R.drawable.ic_motion)
                "Sound" -> sensorIcon.setImageResource(R.drawable.ic_sound)
                "Pressure" -> sensorIcon.setImageResource(R.drawable.ic_pressure)
                "Smoke" -> sensorIcon.setImageResource(R.drawable.ic_smoke)
                "CO2" -> sensorIcon.setImageResource(R.drawable.ic_co2)
            }*/
            view.setOnClickListener {
                onClickListener(result.id.toString())
            }
        }
    }
}