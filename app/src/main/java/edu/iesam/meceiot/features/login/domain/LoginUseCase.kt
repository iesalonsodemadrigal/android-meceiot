package edu.iesam.meceiot.features.login.domain

class LoginUseCase(private val repository: LoginRepository) {
    suspend fun invoke(loginCredentials: LoginCredentials): Boolean {
        return repository.isLoginSuccessful(loginCredentials)
    }
}