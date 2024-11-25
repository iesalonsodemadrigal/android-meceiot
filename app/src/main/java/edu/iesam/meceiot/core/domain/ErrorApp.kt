package edu.iesam.meceiot.core.domain

sealed class ErrorApp : Throwable() {
    object InternetErrorApp : ErrorApp() {
        private fun readResolve(): Any = InternetErrorApp
    }

    object ServerErrorApp : ErrorApp() {
        private fun readResolve(): Any = ServerErrorApp
    }

    object DataErrorApp : ErrorApp() {
        private fun readResolve(): Any = DataErrorApp
    }

    object UnknowErrorApp : ErrorApp() {
        private fun readResolve(): Any = UnknowErrorApp
    }
    object DataExpiredErrorApp : ErrorApp() {
        private fun readResolve(): Any = DataExpiredErrorApp
    }

}