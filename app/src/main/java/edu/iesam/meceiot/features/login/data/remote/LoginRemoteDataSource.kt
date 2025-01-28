package edu.iesam.meceiot.features.login.data.remote

import edu.iesam.meceiot.core.data.remote.apiCall
import edu.iesam.meceiot.features.login.domain.LoginCredentials
import edu.iesam.meceiot.features.login.domain.LoginResponse
import org.koin.core.annotation.Single

@Single
class LoginRemoteDataSource(
    private val apiClient: LoginApiService
) {
    suspend fun login(loginCredentials: LoginCredentials): Result<LoginResponse> {
        return apiCall {
            apiClient.login(loginCredentials)
        }
    }
}