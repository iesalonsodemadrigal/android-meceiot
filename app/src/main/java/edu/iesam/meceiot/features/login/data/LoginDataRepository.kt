package edu.iesam.meceiot.features.login.data

import edu.iesam.meceiot.features.login.data.remote.LoginRemoteDataSource
import edu.iesam.meceiot.features.login.domain.LoginCredentials
import edu.iesam.meceiot.features.login.domain.LoginRepository
import edu.iesam.meceiot.features.login.domain.LoginResponse
import retrofit2.Response

class LoginDataRepository(
    private val remote: LoginRemoteDataSource
) : LoginRepository {
    override suspend fun login(login: LoginCredentials): Response<LoginResponse> {
        return remote.login(login)
    }
}