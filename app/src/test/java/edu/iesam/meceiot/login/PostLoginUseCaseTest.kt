package edu.iesam.meceiot.login

import edu.iesam.meceiot.features.login.domain.LoginCredentials
import edu.iesam.meceiot.features.login.domain.LoginRepository
import edu.iesam.meceiot.features.login.domain.LoginResponse
import edu.iesam.meceiot.features.login.domain.PostLoginUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.Test
import retrofit2.Response

class PostLoginUseCaseTest {
    private val repository: LoginRepository = mockk()
    private val useCase = PostLoginUseCase(repository)

    @Test
    fun `invoke with valid credentials `() = runBlocking {
        val loginCredentials = LoginCredentials("validUser", "validPassword")
        val expectedResponse = Response.success(LoginResponse())

        coEvery { repository.login(loginCredentials) } returns expectedResponse

        val actualResponse = useCase.invoke(loginCredentials)

        assertEquals(expectedResponse, actualResponse)
    }

    @Test
    fun `invoke with invalid credentials `() = runBlocking {
        val loginCredentials = LoginCredentials("invalidUser", "invalidPassword")
        val expectedResponse = Response.error<LoginResponse>(400, mockk(relaxed = true))

        coEvery { repository.login(loginCredentials) } returns expectedResponse

        val actualResponse = useCase.invoke(loginCredentials)

        assertEquals(expectedResponse, actualResponse)
    }

    @Test
    fun `invoke with empty `() = runBlocking {
        val loginCredentials = LoginCredentials("", "")
        val expectedResponse = Response.error<LoginResponse>(400, mockk(relaxed = true))

        coEvery { repository.login(loginCredentials) } returns expectedResponse

        val actualResponse = useCase.invoke(loginCredentials)

        assertEquals(expectedResponse, actualResponse)
    }
}