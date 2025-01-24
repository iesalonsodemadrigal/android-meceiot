package edu.iesam.meceiot.core.presentation.views

import android.content.Context
import edu.iesam.meceiot.core.domain.ErrorApp


class ErrorAppFactory(val context: Context) {

    fun build(errorApp: ErrorApp, onClick: (() -> Unit)): ErrorAppUI {
        return when (errorApp) {
            ErrorApp.DataErrorApp -> ServerErrorAppUI(context, onClick)
            ErrorApp.DataExpiredError -> ConnectionErrorAppUI(context, onClick)
            ErrorApp.InternetErrorApp -> ConnectionErrorAppUI(context, onClick)
            ErrorApp.ServerErrorApp -> ServerErrorAppUI(context, onClick)
            ErrorApp.UnknowErrorApp -> UnknownErrorAppUI(context, onClick)
        }
    }
}