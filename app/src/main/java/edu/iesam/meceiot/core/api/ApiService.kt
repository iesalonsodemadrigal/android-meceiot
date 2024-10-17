package edu.iesam.meceiot.core.api

import edu.iesam.meceiot.features.login.domain.LoginCredentials
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    fun postLogin(login: LoginCredentials)

}