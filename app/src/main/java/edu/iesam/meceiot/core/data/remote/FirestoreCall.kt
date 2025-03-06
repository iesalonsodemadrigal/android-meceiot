package edu.iesam.meceiot.core.data.remote

import edu.iesam.meceiot.core.domain.ErrorApp
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend fun <T : Any> firestoreCall(call: suspend () -> T): Result<T> {
    return try {
        val result = call.invoke()
        Result.success(result)
    } catch (exception: Throwable) {
        when (exception) {
            is ConnectException -> Result.failure(ErrorApp.InternetErrorApp)
            is UnknownHostException -> Result.failure(ErrorApp.ServerErrorApp)
            is SocketTimeoutException -> Result.failure(ErrorApp.InternetErrorApp)
            else -> Result.failure(ErrorApp.UnknowErrorApp)
        }
    }
}