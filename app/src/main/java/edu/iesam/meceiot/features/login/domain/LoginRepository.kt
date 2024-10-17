package edu.iesam.meceiot.features.login.domain

import retrofit2.Response

interface LoginRepository {
    suspend fun postLogin(login: LoginCredentials): Response<LoginResponse>
}