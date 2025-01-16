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
            view.setOnClickListener {
                onClickListener(result.id.toString())
            }
        }
    }
}