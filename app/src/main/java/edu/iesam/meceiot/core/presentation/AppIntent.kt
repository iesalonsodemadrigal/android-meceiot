package edu.iesam.meceiot.core.presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.google.android.material.snackbar.Snackbar
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
        Snackbar.make(
            (context as Activity).findViewById(android.R.id.content),  // Usamos el contenedor raíz de la actividad
            context.getString(R.string.text_sharing_message),  // Mensaje a mostrar
            Snackbar.LENGTH_SHORT  // Duración del Snackbar
        ).show()

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, shareText)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }

        context.startActivity(
            Intent.createChooser(
                intent,
                context.getString(R.string.text_share_title)
            )
        )
    }

    fun openEmail(recipient: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:$recipient")
        }
        context.startActivity(Intent.createChooser(intent, ""))
    }
}


