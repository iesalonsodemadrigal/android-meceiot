package edu.iesam.meceiot.core.domain

sealed class ErrorApp {
    data object InternetErrorApp : ErrorApp()
    data object ServerErrorApp : ErrorApp()
    data object DataErrorApp : ErrorApp()
    data object UnknownErrorApp : ErrorApp()
}