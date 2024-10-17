package edu.iesam.meceiot.features.login.domain

import com.google.gson.annotations.SerializedName

data class LoginCredentials (
    val username: String,
    val password: String
)

data class LoginResponse(
    @SerializedName("code")
    var code: Int,
    @SerializedName("id")
    var id: String,
    @SerializedName("message")
    var message: String
)