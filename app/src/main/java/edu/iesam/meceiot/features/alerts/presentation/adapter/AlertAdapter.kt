package edu.iesam.meceiot.features.alerts.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import edu.iesam.meceiot.R
import edu.iesam.meceiot.features.alerts.domain.Zone

class AlertAdapter : ListAdapter<Zone, AlertViewHolder>(AlertDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlertViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_alert, parent, false)
        return AlertViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlertViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}


class AlertDiffUtil : DiffUtil.ItemCallback<Zone>() {
    override fun areItemsTheSame(oldItem: Zone, newItem: Zone): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Zone, newItem: Zone): Boolean {
        return oldItem == newItem
    }
}