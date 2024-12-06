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

        coEvery { developerRepository.getDevelopers() } returns unsortedDevelopers

        val developers = getDevelopersUseCase()

        coVerify(exactly = 1) { developerRepository.getDevelopers() }
        assertEquals(expectedDevelopers, developers)
    }


    @Test
    fun `deberia devolver error si el repositorio lanza una excepcion`() = runBlocking {
        val exception = Exception("Error al obtener desarrolladores")
        coEvery { developerRepository.getDevelopers() } throws exception

        try {
            getDevelopersUseCase()
        } catch (e: Exception) {
            assertEquals("Error al obtener desarrolladores", e.message)
        }

        coVerify(exactly = 1) { developerRepository.getDevelopers() }
    }


    @Test
    fun `cuando el datasource devuelve una lista vacía de developers y loraWan`() = runBlocking {
    fun `deberia devolver lista vacia con comportamiento controlado si lista inicial es vacia`() =
        runBlocking {
        coEvery { developerRepository.getDevelopers() } returns emptyList()

        coEvery { developerRepository.getDevelopers() } returns Result.success(emptyList())
        val developersInfo = getDevelopersUseCase()
        coVerify(exactly = 1) { developerRepository.getDevelopers() }
        assert(developersInfo.isSuccess)
        assert(developersInfo.getOrNull()?.isEmpty() == true)
    }
            val developers = getDevelopersUseCase()

            assertTrue(developers.isEmpty())

            coVerify(exactly = 1) { developerRepository.getDevelopers() }
        }


    @Test
    fun `deberia manejar duplicados en el repositorio`() = runBlocking {
        val developersDuplicados = listOf(
            DeveloperInfo("1", "Dev1", "url1", "desc1", "other1"),
            DeveloperInfo("1", "Dev1", "url1", "desc1", "other1")
        )

        coEvery { developerRepository.getDevelopers() } returns developersDuplicados

        val developers = getDevelopersUseCase().distinct()

        coVerify(exactly = 1) { developerRepository.getDevelopers() }
        assertEquals(1, developers.size)
    }


    @Test
    fun `deberia devolver un único desarrollador cuando la lista contiene uno solo`() =
        runBlocking {
            val singleDeveloper = listOf(
                DeveloperInfo("1", "Dev1", "url1", "desc1", "other1")
            )

            coEvery { developerRepository.getDevelopers() } returns singleDeveloper

        val developers = getDevelopersUseCase()

        coVerify(exactly = 1) { developerRepository.getDevelopers() }
            assertEquals(singleDeveloper, developers)
    }


    @Test
    fun `deberia manejar desarrolladores con datos incompletos`() = runBlocking {
        val developersIncomplete = listOf(
            DeveloperInfo("2", "", "url2", "", "other2")
        )

        coEvery { developerRepository.getDevelopers() } returns developersIncomplete

        val developers = getDevelopersUseCase()

        coVerify(exactly = 1) { developerRepository.getDevelopers() }
        assertEquals(developersIncomplete, developers)
    }
}
