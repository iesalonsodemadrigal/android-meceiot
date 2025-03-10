package edu.iesam.meceiot.features.login.data.remote

import edu.iesam.meceiot.features.login.domain.LoginCredentials
import edu.iesam.meceiot.features.login.domain.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginApiService {
    @Headers(
        "Accept: */*",
        "Accept-Encoding: gzip, deflate, br",
        "Content-Type: application/json",
        "Origin: https://meceiot.usal.es",
        "Connection: keep-alive",
        "Referer: https://meceiot.usal.es/login.php",
        "Sec-Fetch-Dest: empty",
        "Sec-Fetch-Mode: cors",
        "Sec-Fetch-Site: same-origin",
        "Pragma: no-cache",
        "Cache-Control: no-cache"
    )
    @POST("g/login")
    suspend fun login(@Body loginCredentials: LoginCredentials): Response<LoginResponse>

}