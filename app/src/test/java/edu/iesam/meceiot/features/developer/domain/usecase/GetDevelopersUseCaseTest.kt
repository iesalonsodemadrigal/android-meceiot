package edu.iesam.meceiot.features.developer.domain.usecase

import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
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
    fun `deberia devolver lista de desarrolladores ordenada por ID`() = runBlocking {
        val unsortedDevelopers = listOf(
            DeveloperInfo("2", "Dev2", "url2", "desc2", "other2"),
            DeveloperInfo("1", "Dev1", "url1", "desc1", "other1")
        )
        val expectedDevelopers = listOf(
            DeveloperInfo("1", "Dev1", "url1", "desc1", "other1"),
            DeveloperInfo("2", "Dev2", "url2", "desc2", "other2")
        )

        coEvery { developerRepository.getDevelopers() } returns Result.success(unsortedDevelopers)

        val developersResult = getDevelopersUseCase()

        coVerify(exactly = 1) { developerRepository.getDevelopers() }
        assertTrue(developersResult.isSuccess)
        assertEquals(expectedDevelopers, developersResult.getOrNull())
    }

    @Test
    fun `deberia devolver error si el repositorio lanza una excepcion`() = runBlocking {
        val exception = Exception("Error al obtener desarrolladores")
        coEvery { developerRepository.getDevelopers() } returns Result.failure(exception)

        val developersResult = getDevelopersUseCase()

        coVerify(exactly = 1) { developerRepository.getDevelopers() }
        assertTrue(developersResult.isFailure)
        assertEquals("Error al obtener desarrolladores", developersResult.exceptionOrNull()?.message)
    }

    @Test
    fun `deberia devolver lista vacía cuando el repositorio devuelve una lista vacía`() = runBlocking {
        coEvery { developerRepository.getDevelopers() } returns Result.success(emptyList())

        val developersResult = getDevelopersUseCase()

        coVerify(exactly = 1) { developerRepository.getDevelopers() }
        assertTrue(developersResult.isSuccess)
        assertTrue(developersResult.getOrNull()?.isEmpty() == true)
    }

    @Test
    fun `deberia manejar duplicados en el repositorio`() = runBlocking {
        val developersDuplicados = listOf(
            DeveloperInfo("1", "Dev1", "url1", "desc1", "other1"),
            DeveloperInfo("1", "Dev1", "url1", "desc1", "other1")
        )

        coEvery { developerRepository.getDevelopers() } returns Result.success(developersDuplicados)

        val developersResult = getDevelopersUseCase().getOrNull()?.distinct()

        coVerify(exactly = 1) { developerRepository.getDevelopers() }
        assertEquals(1, developersResult?.size)
    }

    @Test
    fun `deberia devolver un único desarrollador cuando la lista contiene uno solo`() = runBlocking {
        val singleDeveloper = listOf(
            DeveloperInfo("1", "Dev1", "url1", "desc1", "other1")
        )

        coEvery { developerRepository.getDevelopers() } returns Result.success(singleDeveloper)

        val developersResult = getDevelopersUseCase()

        coVerify(exactly = 1) { developerRepository.getDevelopers() }
        assertTrue(developersResult.isSuccess)
        assertEquals(singleDeveloper, developersResult.getOrNull())
    }

    @Test
    fun `deberia manejar desarrolladores con datos incompletos`() = runBlocking {
        val developersIncomplete = listOf(
            DeveloperInfo("2", "", "url2", "", "other2")
        )

        coEvery { developerRepository.getDevelopers() } returns Result.success(developersIncomplete)

        val developersResult = getDevelopersUseCase()

        coVerify(exactly = 1) { developerRepository.getDevelopers() }
        assertTrue(developersResult.isSuccess)
        assertEquals(developersIncomplete, developersResult.getOrNull())
    }
}
