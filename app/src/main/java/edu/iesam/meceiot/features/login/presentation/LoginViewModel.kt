package edu.iesam.meceiot.features.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.meceiot.core.domain.ErrorApp
import edu.iesam.meceiot.features.login.domain.LoginCredentials
import edu.iesam.meceiot.features.login.domain.LoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<LoginUiState>()
    val uiState: LiveData<LoginUiState> = _uiState

    fun postLoginCredentials(loginCredentials: LoginCredentials) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.postValue(LoginUiState(isLoading = true))

            val isLoginSuccessful = loginUseCase.invoke(loginCredentials)
            _uiState.postValue(
                LoginUiState(
                    isLoading = false,
                    loginCredentials = loginCredentials, //Solo para pruebas
                    errorApp = isLoginSuccessful.exceptionOrNull() as ErrorApp?,
                    loginSuccessful = isLoginSuccessful.getOrElse { false }
                )
            )

        }
    }

    data class LoginUiState(
        val isLoading: Boolean = false,
        val errorApp: ErrorApp? = null,
        val loginCredentials: LoginCredentials? = null, //Solo para pruebas
        val loginSuccessful: Boolean = false
    )
}


