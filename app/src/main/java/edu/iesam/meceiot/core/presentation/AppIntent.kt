package edu.iesam.meceiot.core.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import org.koin.core.annotation.Single

@Single
class AppIntent(private val context: Context) {
    fun openUrl(url: String) {
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(url)
            )
        )
    }

    // Add more intent methods here if needed
}