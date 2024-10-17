package edu.iesam.meceiot.features.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.meceiot.core.domain.ErrorApp
import edu.iesam.meceiot.features.login.domain.PostLoginCredentialsUseCase
import edu.iesam.meceiot.features.login.domain.LoginCredentials
import edu.iesam.meceiot.features.login.domain.LoginResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel(
    private val postLoginCredentialsUseCase: PostLoginCredentialsUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<LoginUiState>()
    val uiState: LiveData<LoginUiState> = _uiState

    fun postLoginCredentials(login: LoginCredentials) {
        viewModelScope.launch {
            _uiState.value = LoginUiState(isLoading = true)
            try {
                val response: Response<LoginResponse> = postLoginCredentialsUseCase.invoke(login)
                _uiState.value = LoginUiState(isLoading = false, loginCredentials = login, loginResponse = response.body())
            } catch (e: Exception) {
                //El tipo de error es un placeholder
                _uiState.value = LoginUiState(isLoading = false, errorApp = ErrorApp.UnknownErrorApp)
            }
        }
    }

    data class LoginUiState (
        val isLoading: Boolean = false,
        val errorApp: ErrorApp? = null,
        val loginCredentials: LoginCredentials? = null,
        val loginResponse: LoginResponse? = null

    )

}


