package edu.iesam.meceiot.features.setting.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import edu.iesam.meceiot.BuildConfig
import edu.iesam.meceiot.R

class SettingAboutFragment : Fragment(R.layout.fragment_settings) {

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCardViews(view)
        view.findViewById<TextView>(R.id.text_view_version).text = "Version: ${BuildConfig.VERSION_NAME}"
    }

    private fun setupCardViews(view: View) {
        listOf(
            R.id.card_collaborators to { navigateTo(SettingAboutFragmentDirections.actionSettingsToDeveloperAboutFragment()) },
            R.id.card_resources to { navigateTo(SettingAboutFragmentDirections.actionSettingsToExternalResourcesFragment()) },
            R.id.card_contact_us to { startActivity(Intent.createChooser(share, "Choose an email app")) },
            R.id.card_play_store to { openWebsite("https://play.google.com/store/apps/details?id=edu.iesam.meceiot") },
            R.id.card_project_website to { openWebsite("https://siadenlab.iesalonsodemadrigal.es/meceiot") },
            R.id.card_privacy_policy to { openWebsite("https://siadenlab.iesalonsodemadrigal.es/meceiot/legal.html") }
        ).forEach { (id, action) ->
            view.findViewById<CardView>(id).setOnClickListener { action() }
        }
    }

    private fun navigateTo(action: NavDirections) {
        findNavController().navigate(action)
    }

    private fun openWebsite(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    private val imageUris: ArrayList<Uri> = arrayListOf()
    private val share: Intent = Intent().apply {
        action = Intent.ACTION_SEND_MULTIPLE
        putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris)
        putExtra(Intent.EXTRA_EMAIL, arrayOf("example@example.com"))
        putExtra(Intent.EXTRA_SUBJECT, "Check this out!")
        putExtra(Intent.EXTRA_TEXT, "https://developer.android.com/training/sharing/")
        type = "image/*"
    }
}
