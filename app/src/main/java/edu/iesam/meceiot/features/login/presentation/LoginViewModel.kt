package edu.iesam.meceiot.features.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.meceiot.core.domain.ErrorApp
import edu.iesam.meceiot.features.login.domain.LoginCredentials
import edu.iesam.meceiot.features.login.domain.LoginResponse
import edu.iesam.meceiot.features.login.domain.PostLoginCredentialsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel(
    private val postLoginCredentialsUseCase: PostLoginCredentialsUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<LoginUiState>()
    val uiState: LiveData<LoginUiState> = _uiState

    fun postLoginCredentials(login: LoginCredentials) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.postValue(LoginUiState(isLoading = true))
            val response: Response<LoginResponse> = postLoginCredentialsUseCase.invoke(login)
            if (response.isSuccessful) {
                _uiState.postValue(
                    LoginUiState(
                        isLoading = false,
                        loginCredentials = login,
                        loginResponse = response.body()
                    )
                )
            } else {
                //El error que devuelve es un placeholder
                _uiState.postValue(
                    LoginUiState(
                        isLoading = false,
                        errorApp = ErrorApp.UnknownErrorApp,
                        loginResponse = response.body()
                    )
                )
            }

        }
    }

    data class LoginUiState(
        val isLoading: Boolean = false,
        val errorApp: ErrorApp? = null,
        val loginCredentials: LoginCredentials? = null,
        val loginResponse: LoginResponse? = null

    )

}


