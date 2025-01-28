package edu.iesam.meceiot.features.login.domain

import org.koin.core.annotation.Single

@Single
class LoginUseCase(
    private val repository: LoginRepository
) {
    suspend operator fun invoke(loginCredentials: LoginCredentials): Result<Boolean> {
        return repository.isLoginSuccessful(loginCredentials)
    }
}