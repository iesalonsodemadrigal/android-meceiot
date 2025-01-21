package edu.iesam.meceiot.core.presentation.views

import android.content.Context
import edu.iesam.meceiot.core.domain.ErrorApp


class ErrorAppFactory(val context: Context) {

    fun build(errorApp: ErrorApp): ErrorAppUI {
        return when (errorApp) {
            ErrorApp.DataErrorApp -> ServerErrorAppUI(context)
            ErrorApp.DataExpiredError -> ConnectionErrorAppUI(context)
            ErrorApp.InternetErrorApp -> ConnectionErrorAppUI(context)
            ErrorApp.ServerErrorApp -> ServerErrorAppUI(context)
            ErrorApp.UnknowErrorApp -> ServerErrorAppUI(context)
        }
    }
}