package edu.iesam.meceiot.features.login.domain

interface LoginRepository {
    suspend fun postLogin(login: LoginCredentials)
}