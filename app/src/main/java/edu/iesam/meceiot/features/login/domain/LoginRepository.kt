package edu.iesam.meceiot.features.login.domain

import retrofit2.Response

interface LoginRepository {
    suspend fun login(login: LoginCredentials): Response<LoginResponse>
}