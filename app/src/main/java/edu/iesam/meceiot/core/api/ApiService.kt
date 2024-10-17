package edu.iesam.meceiot.core.api

import edu.iesam.meceiot.features.login.domain.LoginCredentials
import edu.iesam.meceiot.features.login.domain.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    fun postLogin(@Body login: LoginCredentials): Response<LoginResponse>

}