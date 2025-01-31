package edu.iesam.meceiot.features.login.data.local

import org.koin.core.annotation.Single

@Single
class SessionManager(
    private val local: LoginXmlDataSource
) {
    fun isLoggedIn(): Boolean = local.getCredentials() != null

    fun logout() {
        local.deleteCredentials()
    }
}