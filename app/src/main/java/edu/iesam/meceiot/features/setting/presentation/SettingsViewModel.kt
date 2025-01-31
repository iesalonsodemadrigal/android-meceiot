package edu.iesam.meceiot.features.setting.presentation

import androidx.lifecycle.ViewModel
import edu.iesam.meceiot.features.login.data.local.LoginXmlDataSource
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SettingsViewModel(
    private val localDataSource: LoginXmlDataSource
) : ViewModel() {

    fun logout() {
        localDataSource.deleteCredentials()
    }
}