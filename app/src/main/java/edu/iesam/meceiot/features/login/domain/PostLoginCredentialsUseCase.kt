package edu.iesam.meceiot.features.login.domain

class PostLoginCredentialsUseCase(private val repository: LoginRepository) {
    suspend fun invoke(login: LoginCredentials) {
        return repository.postLogin(login)
    }
}