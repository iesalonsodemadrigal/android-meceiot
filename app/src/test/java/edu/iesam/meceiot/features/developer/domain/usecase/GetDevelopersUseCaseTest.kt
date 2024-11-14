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
    fun `empty list`() = runBlocking {
        coEvery { developerRepository.getDevelopers() } returns Result.success(emptyList())
        val result = getDevelopersUseCase()
        coVerify(exactly = 1) { developerRepository.getDevelopers() }
        assert(result.isSuccess)
    }

    @Test
    fun `correct list`() = runBlocking {
        val developerExpected = listOf(
            DeveloperInfo(
                "d1",
                "d1",
                "d1/wp-content/uploads/2022/11/LoRaWAN_Logo.svg_.png",
                "d1",
                "d1"
            ),
            DeveloperInfo(
                "d2",
                "D2",
                "d2",
                "d2",
                "d2"
            )
        )

        coEvery { developerRepository.getDevelopers() } returns Result.success(developerExpected)
        val result = getDevelopersUseCase()
        coVerify(exactly = 1) { developerRepository.getDevelopers() }
        val resultValue = result.getOrNull()
        assertEquals(developerExpected, resultValue)
    }

}

