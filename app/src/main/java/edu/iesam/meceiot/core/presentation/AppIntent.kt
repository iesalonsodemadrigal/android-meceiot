package edu.iesam.meceiot.core.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import edu.iesam.meceiot.R
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
    fun shareApp(shareText: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, shareText)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(Intent.createChooser(intent, R.string.text_appstore.toString()))
    }

    fun openEmail(recipient: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:$recipient")
        }

        try {
            val emailApp = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$recipient"))
            emailApp.setPackage("com.google.android.gm") // Gmail
            context.startActivity(emailApp)
        } catch (e: Exception) {
            try {
                val hotmailApp = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$recipient"))
                hotmailApp.setPackage("com.microsoft.office.outlook") // Hotmail/Outlook
                context.startActivity(hotmailApp)
            } catch (e: Exception) {
                try {
                    context.startActivity(Intent.createChooser(intent, "Elige una app para enviar el correo"))
                } catch (e: Exception) {
                    println("No se encontró una app para enviar correos.")
                }
            }
        }
    }


}