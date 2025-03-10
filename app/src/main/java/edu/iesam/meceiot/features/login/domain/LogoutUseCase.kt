package edu.iesam.meceiot.features.login.domain

import org.koin.core.annotation.Single

@Single
class LogoutUseCase(
    private val loginRepository: LoginRepository
) {
    operator fun invoke() {
        loginRepository.logout()
    }
}