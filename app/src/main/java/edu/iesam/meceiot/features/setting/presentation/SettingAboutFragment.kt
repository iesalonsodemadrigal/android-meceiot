package edu.iesam.meceiot.features.setting.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import edu.iesam.meceiot.BuildConfig
import edu.iesam.meceiot.R
import edu.iesam.meceiot.databinding.FragmentSettingsBinding

class SettingAboutFragment : Fragment(R.layout.fragment_settings) {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSettingsBinding.bind(view)
        setupCardViews()
        binding.textViewVersion.text = "Version: ${BuildConfig.VERSION_NAME}"
    }

    private fun setupCardViews() {
        listOf(
            R.id.card_collaborators to { navigateTo(SettingAboutFragmentDirections.actionSettingsToDeveloperAboutFragment()) },
            R.id.card_resources to { navigateTo(SettingAboutFragmentDirections.actionSettingsToExternalResourcesFragment()) },
            R.id.card_contact_us to { startActivity(Intent.createChooser(share(), "Choose an email app")) },
            R.id.card_play_store to { openWebsite("https://play.google.com/store/apps/details?id=edu.iesam.meceiot") },
            R.id.card_project_website to { openWebsite("https://siadenlab.iesalonsodemadrigal.es/meceiot") },
            R.id.card_privacy_policy to { openWebsite("https://siadenlab.iesalonsodemadrigal.es/meceiot/legal.html") }
        ).forEach { (id, action) ->
            binding.root.findViewById<CardView>(id).setOnClickListener { action() }
        }
    }

    private fun navigateTo(action: NavDirections) {
        findNavController().navigate(action)
    }

    private fun openWebsite(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    private val imageUris: ArrayList<Uri> = arrayListOf()

    private fun share(): Intent {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris)
            putExtra(Intent.EXTRA_EMAIL, arrayOf("info@siadenlab.es"))
            putExtra(Intent.EXTRA_SUBJECT, "Solicitud de colaboración - APP MECEIOT")
            type = "image/*"
            `package` = "com.google.android.gm"  // Intent exclusivo para Gmail
        }
        try {
            startActivity(Intent.createChooser(shareIntent, "Choose an email app"))
        } catch (e: android.content.ActivityNotFoundException) {
            shareIntent.`package` = "com.microsoft.office.outlook"
            try {
                startActivity(Intent.createChooser(shareIntent, "Choose an email app"))
            } catch (e: android.content.ActivityNotFoundException) {
                Toast.makeText(context, "Neither Gmail nor Hotmail is installed.", Toast.LENGTH_SHORT).show()
            }
        }

        return shareIntent
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
