package edu.iesam.meceiot.features.login.domain

import retrofit2.Response

class LoginUseCase(private val repository: LoginRepository) {
    suspend fun invoke(user: String, password: String): Boolean {
        return repository.login(user, password)
    }
}