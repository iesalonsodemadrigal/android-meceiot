package edu.iesam.meceiot.features.login.domain

interface LoginRepository {
    suspend fun isLoginSuccessful(loginCredentials: LoginCredentials): Boolean
}