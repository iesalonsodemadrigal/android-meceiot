package edu.iesam.meceiot.features.developer.domain.usecaseimport

import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo
import edu.iesam.meceiot.features.developer.domain.usecase.DeveloperRepository
import edu.iesam.meceiot.features.developer.domain.usecase.GetDevelopersUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
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
    fun `lista vacia`() = runBlockingTest {

        coEvery { developerRepository.getDevelopers() } returns emptyList()


        val developer = getDevelopersUseCase()


        coVerify(exactly = 1) { developerRepository.getDevelopers() }
        assert(developer.isEmpty())
    }

    @Test
    fun `lista correcta`() = runBlockingTest {

        val developerExpected = listOf(
            DeveloperInfo(
                "d1",
                "d1",
                "d1/wp-content/uploads/2022/11/LoRaWAN_Logo.svg_.png",
                "d1"
            ),
            DeveloperInfo(
                "d2",
                "D2",
                "d2",
                "d2"
            )
        )
        coEvery { developerRepository.getDevelopers() } returns developerExpected

        // Ejecutar el caso de uso
        val developerReceived = getDevelopersUseCase()

        // Verificar la llamada y los datos recibidos
        coVerify(exactly = 1) { developerRepository.getDevelopers() }
        Assert.assertEquals(developerExpected, developerReceived)
    }
}
