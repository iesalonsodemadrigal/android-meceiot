package edu.iesam.meceiot.features.login.domain

data class LoginCredentials(
    val user: String,
    val password: String
)

data class LoginResponse(
    val isSuccessful: Boolean
)