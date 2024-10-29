package edu.iesam.meceiot.features.login.presentation

import android.content.Context
import edu.iesam.meceiot.features.login.data.LoginDataRepository
import edu.iesam.meceiot.features.login.data.remote.LoginRemoteDataSource
import edu.iesam.meceiot.features.login.domain.LoginUseCase

class LoginFactory(context: Context) {
    private val loginRemoteDataSource = LoginRemoteDataSource()
    private val loginDataRepository = LoginDataRepository(loginRemoteDataSource)
    private val getLoginCredentialsUseCase = LoginUseCase(loginDataRepository)

    fun buildViewModel(): LoginViewModel {
        return LoginViewModel(
            getLoginCredentialsUseCase
        )
    }

}