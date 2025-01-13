package edu.iesam.meceiot.features.setting.presentation

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import edu.iesam.meceiot.R
import edu.iesam.meceiot.features.setting.data.SettingDataRepository
import edu.iesam.meceiot.features.setting.data.local.xml.SettingXmlLocalDataSource

class Settings : Fragment(R.layout.fragment_settings) {

    // Crear la instancia del repositorio, pasando el SettingXmlLocalDataSource
    private val settingDataRepository by lazy {
        SettingDataRepository(SettingXmlLocalDataSource(requireContext()))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtén la versión de la aplicación desde SharedPreferences o desde la versión actual
        val version = settingDataRepository.getAppVersionFromPrefs() ?: settingDataRepository.getAppVersion()

        // Muestra la versión de la aplicación en el TextView
        val versionTextView: TextView = view.findViewById(R.id.text_view_version)
        versionTextView.text = "Versión: $version"  // Establecer la versión aquí

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
