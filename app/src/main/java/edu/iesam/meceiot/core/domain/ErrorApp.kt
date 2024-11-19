package edu.iesam.meceiot.core.domain

sealed class ErrorApp : Throwable() {
    object InternetErrorApp : ErrorApp()
    object ServerErrorApp : ErrorApp()
    object DataErrorApp : ErrorApp()
    object UnknownErrorApp : ErrorApp()
}