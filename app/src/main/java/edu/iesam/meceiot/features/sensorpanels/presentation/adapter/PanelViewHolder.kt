package edu.iesam.meceiot.features.sensorpanels.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import edu.iesam.meceiot.databinding.ItemPanelBinding
import edu.iesam.meceiot.features.sensorpanels.domain.Panel

class PanelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val itemPanel = ItemPanelBinding.bind(view)

    fun bind(result: Panel, onClickListener: (String) -> Unit) {
        itemPanel.apply {
            panelName.text = result.name
        }
    }
}