package edu.iesam.meceiot.features.login.data

import edu.iesam.meceiot.features.login.data.local.LoginXmlDataSource
import edu.iesam.meceiot.features.login.data.remote.LoginRemoteDataSource
import edu.iesam.meceiot.features.login.domain.LoginCredentials
import edu.iesam.meceiot.features.login.domain.LoginRepository
import org.koin.core.annotation.Single

@Single
class LoginDataRepository(
    private val remote: LoginRemoteDataSource,
    private val local: LoginXmlDataSource
) : LoginRepository {
    override suspend fun isLoginSuccessful(loginCredentials: LoginCredentials): Result<Boolean> {
        val response = remote.login(loginCredentials)
        return if (response.isSuccess) {
            local.saveCredentials(loginCredentials)
            Result.success(true)
        } else {
            Result.failure(response.exceptionOrNull()!!)
        }
    }
}