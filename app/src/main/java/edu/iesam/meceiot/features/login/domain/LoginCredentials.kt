package edu.iesam.meceiot.features.login.domain

data class LoginCredentials(
    val username: String,
    val password: String
)

data class LoginResponse(
    val token: String,
    val userId: String,
    val message: String
)