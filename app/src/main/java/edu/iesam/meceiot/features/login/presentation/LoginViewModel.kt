package edu.iesam.meceiot.features.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.meceiot.core.domain.ErrorApp
import edu.iesam.meceiot.features.login.domain.PostLoginCredentialsUseCase
import edu.iesam.meceiot.features.login.domain.LoginCredentials
import kotlinx.coroutines.launch

class LoginViewModel(
    private val postLoginCredentialsUseCase: PostLoginCredentialsUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<LoginUiState>()
    val uiState: LiveData<LoginUiState> = _uiState

    fun postLoginCredentials() {
        viewModelScope.launch {
            //TODO()
        }
    }

    data class LoginUiState (
        val isLoading: Boolean = false,
        val errorApp: ErrorApp? = null,
        val loginCredentials: LoginCredentials? = null

    )

}


