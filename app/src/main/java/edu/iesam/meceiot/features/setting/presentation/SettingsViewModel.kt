package edu.iesam.meceiot.features.setting.presentation

import androidx.lifecycle.ViewModel
import edu.iesam.meceiot.features.login.domain.LogoutUseCase
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SettingsViewModel(
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    fun logout() {
        logoutUseCase.invoke()
    }
}