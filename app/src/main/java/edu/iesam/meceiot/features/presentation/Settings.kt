package edu.iesam.meceiot.features.presentation

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import edu.iesam.meceiot.R

class Settings : Fragment(R.layout.fragment_settings) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val navigateTextViewDeveloper: TextView = view.findViewById(R.id.text_view_collaborators)
        navigateTextViewDeveloper.setOnClickListener {
            navigateTo(SettingsDirections.actionSettingsToDeveloperAboutFragment())
        }

        val navigateTextViewExternalResources: TextView = view.findViewById(R.id.text_view_resources)
        navigateTextViewExternalResources.setOnClickListener {
            navigateTo(SettingsDirections.actionSettingsToExternalResourcesFragment())
        }
    }

    private fun navigateTo(action: NavDirections) {
        findNavController().navigate(action)
    }
}
