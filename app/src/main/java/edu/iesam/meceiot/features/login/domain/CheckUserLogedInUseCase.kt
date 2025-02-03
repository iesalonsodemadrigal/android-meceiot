package edu.iesam.meceiot.features.login.domain

import org.koin.core.annotation.Single

@Single
class CheckUserLogedInUseCase(
    private val loginRepository: LoginRepository
) {
    operator fun invoke(): Boolean {
        return loginRepository.isUserLoggedIn()
    }
}