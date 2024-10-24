package edu.iesam.meceiot.features.developer.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.android_meceiot.R
import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo

class DeveloperAdapter : ListAdapter<DeveloperInfo, DeveloperViewHolder>(DeveloperDifUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeveloperViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_developer, parent, false)
        return DeveloperViewHolder(view)
    }

    override fun onBindViewHolder(holder: DeveloperViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}