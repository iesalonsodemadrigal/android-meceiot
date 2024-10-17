package edu.iesam.meceiot.features.login.data.remote

import edu.iesam.meceiot.core.api.ApiClient
import edu.iesam.meceiot.features.login.domain.LoginCredentials

class LoginRemoteDataSource {
    private val apiClient = ApiClient()

    suspend fun postLogin(login: LoginCredentials) {
        apiClient.apiService.postLogin(login)
    }
}