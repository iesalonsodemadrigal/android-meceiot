package edu.iesam.meceiot.core.domain

sealed class ErrorApp {
    object InternetErrorApp : ErrorApp()
    object DatabaseErrorApp : ErrorApp()
    object UnknowErrorApp : ErrorApp()
    object ServerErrorApp : ErrorApp()
}