package edu.iesam.meceiot.features.login.domain

interface LoginRepository {
    suspend fun isLoginSuccessful(loginCredentials: LoginCredentials): Result<Boolean>
    fun logout()
    fun isUserLoggedIn(): Boolean
}