package edu.iesam.meceiot.features.login.data.remote

import edu.iesam.meceiot.core.api.ApiClient
import edu.iesam.meceiot.features.login.domain.LoginCredentials
import edu.iesam.meceiot.features.login.domain.LoginResponse
import retrofit2.Response

class LoginRemoteDataSource {
    private val apiClient = ApiClient()

    suspend fun login(login: LoginCredentials): Response<LoginResponse> {
        return apiClient.apiService.login(login)
    }
}