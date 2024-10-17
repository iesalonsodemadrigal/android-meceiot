package edu.iesam.meceiot.features.login.data

import edu.iesam.meceiot.features.login.data.remote.LoginRemoteDataSource
import edu.iesam.meceiot.features.login.domain.LoginCredentials
import edu.iesam.meceiot.features.login.domain.LoginRepository

class LoginDataRepository(
    private val remote: LoginRemoteDataSource
): LoginRepository {
    override suspend fun postLogin(login: LoginCredentials) {
        remote.postLogin(login)

    }
}