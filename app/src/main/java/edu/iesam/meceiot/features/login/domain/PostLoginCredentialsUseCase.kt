package edu.iesam.meceiot.features.login.domain

import retrofit2.Response

class PostLoginCredentialsUseCase(private val repository: LoginRepository) {
    suspend fun invoke(login: LoginCredentials): Response<LoginResponse> {
        return repository.postLogin(login)
    }
}