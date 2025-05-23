package edu.iesam.meceiot.features.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.meceiot.core.domain.ErrorApp
import edu.iesam.meceiot.features.login.domain.CheckUserLogedInUseCase
import edu.iesam.meceiot.features.login.domain.LoginCredentials
import edu.iesam.meceiot.features.login.domain.LoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val checkLoginUseCase: CheckUserLogedInUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<LoginUiState>()
    val uiState: LiveData<LoginUiState> = _uiState

    fun checkUserLoggedIn() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.postValue(LoginUiState(isLoading = true, isFormEnabled = false))

            val isUserLoggedIn = checkLoginUseCase.invoke()
            if (isUserLoggedIn) {
                _uiState.postValue(
                    LoginUiState(
                        userLoggedIn = true,
                        isLoading = false,
                        isFormEnabled = false
                    )
                )
            } else {
                _uiState.postValue(
                    LoginUiState(
                        userLoggedIn = false,
                        isLoading = false,
                        isFormEnabled = true
                    )
                )
            }
        }
    }

    fun postLoginCredentials(loginCredentials: LoginCredentials) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.postValue(LoginUiState(isLoading = true, isFormEnabled = false))

            val loginResult = loginUseCase.invoke(loginCredentials)
            _uiState.postValue(
                LoginUiState(
                    isLoading = false,
                    errorApp = loginResult.exceptionOrNull() as? ErrorApp,
                    userLoggedIn = loginResult.isSuccess,
                    isFormEnabled = !loginResult.isSuccess
                )
            )
        }
    }

    data class LoginUiState(
        val isLoading: Boolean = false,
        val errorApp: ErrorApp? = null,
        val userLoggedIn: Boolean = false,
        val isFormEnabled: Boolean = false
    )
}


