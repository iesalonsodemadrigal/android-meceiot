package edu.iesam.meceiot.features.setting.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.iesam.meceiot.features.login.domain.LogoutUseCase
import edu.iesam.meceiot.features.setting.domain.GetUserUseCase
import edu.iesam.meceiot.features.setting.domain.User
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SettingsViewModel(
    private val logoutUseCase: LogoutUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun logout() {
        logoutUseCase.invoke()
    }

    fun getUser() {
        _uiState.value = UiState(isLoading = true)
        val user = getUserUseCase.invoke()
        _uiState.postValue(
            UiState(
                user = user,
                isLoading = false
            )
        )
    }

    data class UiState(
        val isLoading: Boolean = false,
        val user: User? = null
    )

}