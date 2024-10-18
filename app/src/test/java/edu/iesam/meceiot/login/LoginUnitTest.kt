package edu.iesam.meceiot.login

import edu.iesam.meceiot.features.login.domain.LoginCredentials
import edu.iesam.meceiot.features.login.domain.LoginRepository
import edu.iesam.meceiot.features.login.domain.LoginResponse
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Response

class LoginUnitTest {
    @Mock
    private lateinit var loginRepository: LoginRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `test Login Success`() {
        runBlocking {
            // Given
            val login = LoginCredentials("testUser", "testPassword")

            val expectedResult = Response.success(LoginResponse("true", "1", "testUser"))

            // When
            `when`(loginRepository.login(login)).thenReturn(expectedResult)
            val result = loginRepository.login(login)

            // Then
            assert(result.body() == expectedResult.body())
        }
    }
}