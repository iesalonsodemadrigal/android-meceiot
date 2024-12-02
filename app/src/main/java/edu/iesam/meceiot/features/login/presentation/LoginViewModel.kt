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

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<LoginUiState>()
    val uiState: LiveData<LoginUiState> = _uiState

    fun postLoginCredentials(loginCredentials: LoginCredentials) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.postValue(LoginUiState(isLoading = true))
            val isLoginSuccessful: Boolean = loginUseCase.invoke(loginCredentials)
            if (isLoginSuccessful) {
                _uiState.postValue(
                    LoginUiState(
                        isLoading = false,
                        loginCredentials = loginCredentials, //Solo para pruebas
                        loginSuccessful = true
                    )
                )
            } else {
                //El error que devuelve es un placeholder
                _uiState.postValue(
                    LoginUiState(
                        isLoading = false,
                        errorApp = ErrorApp.UnknowErrorApp,
                        loginSuccessful = false,
                        loginCredentials = loginCredentials
                    )
                )
            }
        }
    }

    data class LoginUiState(
        val isLoading: Boolean = false,
        val errorApp: ErrorApp? = null,
        val loginCredentials: LoginCredentials? = null, //Solo para pruebas
        val loginSuccessful: Boolean = false
    )
}


