package edu.iesam.meceiot.features.sensorpanels.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.iesam.meceiot.databinding.ItemPanelBinding
import edu.iesam.meceiot.databinding.ItemSensorBinding
import edu.iesam.meceiot.features.sensorpanels.domain.Panel
import edu.iesam.meceiot.features.sensorpanels.domain.Sensor

class SensorPanelsAdapter(private val items: List<ListItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ListItem.Type.PANEL.value -> {
                val view = ItemPanelBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ).root
                PanelViewHolder(view)
            }

            ListItem.Type.SENSOR.value -> {
                val view = ItemSensorBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ).root
                SensorViewHolder(view)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PanelViewHolder -> holder.bind(items[position] as Panel, onClickListener)
            is SensorViewHolder -> holder.bind(items[position] as Sensor, onClickListener)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].getType()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private var onClickListener: (String) -> Unit = {}

    fun setOnClickListener(onClickListener: (String) -> Unit) {
        this.onClickListener = onClickListener
    }
}