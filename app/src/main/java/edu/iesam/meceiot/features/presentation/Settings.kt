package edu.iesam.meceiot.features.presentation

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import edu.iesam.meceiot.R

class Settings : Fragment(R.layout.fragment_settings) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navigateButtonDeveloper: Button = view.findViewById(R.id.button_navigate_developer)
        navigateButtonDeveloper.setOnClickListener {
            navigateTo(SettingsDirections.actionSettingsToDeveloperAboutFragment())
        }

        val navigateButtonExternalResources: Button = view.findViewById(R.id.button_navigate_externalResource)
        navigateButtonExternalResources.setOnClickListener {
            navigateTo(SettingsDirections.actionSettingsToExternalResourcesFragment())
        }
    }

    private fun navigateTo(action: NavDirections) {
        findNavController().navigate(action)
    }
}
