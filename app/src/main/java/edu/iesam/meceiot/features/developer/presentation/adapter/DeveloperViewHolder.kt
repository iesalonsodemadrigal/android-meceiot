package edu.iesam.meceiot.features.developer.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.android_meceiot.databinding.ItemDeveloperBinding
import edu.iesam.meceiot.core.extensions.loadUrl
import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo

class DeveloperViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private lateinit var binding: ItemDeveloperBinding

    fun bind(developerInfo: DeveloperInfo) {
        binding = ItemDeveloperBinding.bind(view)

        binding.apply {
            developerName.text = developerInfo.full_Name.takeIf { !it.isNullOrEmpty() }
                ?: "Nombre no disponible" // Asigna un valor predeterminado si el nombre es nulo o vacío
            developerImage.loadUrl(developerInfo.url_Avatar.takeIf { it.isNotEmpty() }
                ?: "URL de imagen por defecto") // Usa una imagen por defecto si la URL está vacía
            developerDescription.text = developerInfo.college_Degree.takeIf { !it.isNullOrEmpty() }
                ?: "Descripción no disponible" // Asigna un valor predeterminado si la descripción es nula o vacía
        }


    }
}