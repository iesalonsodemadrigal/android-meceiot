package edu.iesam.meceiot.features.login.data

import edu.iesam.meceiot.features.login.data.remote.LoginRemoteDataSource
import edu.iesam.meceiot.features.login.domain.LoginCredentials
import edu.iesam.meceiot.features.login.domain.LoginRepository

class LoginDataRepository(
    private val remote: LoginRemoteDataSource
) : LoginRepository {
    override suspend fun login(user: String, password: String): Boolean {
        return remote.login(user, password)
    }
}