package edu.iesam.meceiot.features.login.data.remote

import edu.iesam.meceiot.core.api.retrofit.ApiClient
import edu.iesam.meceiot.features.login.domain.LoginCredentials

class LoginRemoteDataSource {
    private val apiClient = ApiClient()

    suspend fun login(user: String, password: String): Boolean {
        return apiClient.apiService.login(user,password)
    }
}