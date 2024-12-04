package edu.iesam.meceiot.features.developer.domain.usecase

import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class GetDevelopersUseCaseTest {

    @RelaxedMockK
    private lateinit var developerRepository: DeveloperRepository
    private lateinit var getDevelopersUseCase: GetDevelopersUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getDevelopersUseCase = GetDevelopersUseCase(developerRepository)
    }

    @Test
    fun `lista vacia`() = runBlocking {

        coEvery { developerRepository.getDevelopers() } returns emptyList()

        val developers = getDevelopersUseCase()

        coVerify(exactly = 1) { developerRepository.getDevelopers() }
        assert(developers.isEmpty())
    }

    @Test
    fun `lista correcta`() = runBlocking {

        val developerExpected = listOf(
            DeveloperInfo(
                "1",
                "d1",
                "d1/wp-content/uploads/2022/11/LoRaWAN_Logo.svg_.png",
                "d1",
                "d1"
            ),
            DeveloperInfo(
                "2",
                "D2",
                "d2",
                "d2",
                "d2"
            )
        )
        coEvery { developerRepository.getDevelopers() } returns developerExpected

        // Ejecutar el caso de uso
        val developerReceived = getDevelopersUseCase()

        // Verificar la llamada y los datos recibidos
        coVerify(exactly = 1) { developerRepository.getDevelopers() }
        assertEquals(developerExpected, developerReceived)
    }
}
