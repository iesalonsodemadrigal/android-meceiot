package edu.iesam.meceiot.core.presentation.views

import android.content.Context
import androidx.fragment.app.Fragment
import edu.iesam.meceiot.core.domain.ErrorApp


class ErrorAppFactory(val context: Context) {

    fun build(errorApp: ErrorApp, retryFragment: Fragment): ErrorAppUI {
        return when (errorApp) {
            ErrorApp.DataErrorApp -> ServerErrorAppUI(context, retryFragment)
            ErrorApp.DataExpiredError -> ConnectionErrorAppUI(context, retryFragment)
            ErrorApp.InternetErrorApp -> ConnectionErrorAppUI(context, retryFragment)
            ErrorApp.ServerErrorApp -> ServerErrorAppUI(context, retryFragment)
            ErrorApp.UnknowErrorApp -> UnknownErrorAppUI(context, retryFragment)
        }
    }
}
