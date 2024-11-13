package edu.iesam.meceiot.features.externalresources.domain

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GetAllExternalResourcesUseCaseTest {

    private val externalResourcesRepository = mockk<ExternalResourcesRepository>()
    private val getAllExternalResourcesUseCase =
        GetAllExternalResourcesUseCase(externalResourcesRepository)

    @Test
    fun returnsAllExternalResources() = runBlocking {
        val expectedResources = listOf(
            ExternalResources(
                "Resource 1",
                "asd",
                "http://resource1.jpeg",
                "http://resource1.com/"
            ),
            ExternalResources(
                "Resource 2",
                "asd",
                "http://resource2.jpeg",
                "http://resource2.com/"
            ),
        )
        coEvery { externalResourcesRepository.getAllExternalResources() } returns expectedResources

        val result = getAllExternalResourcesUseCase.invoke()

        assertEquals(expectedResources, result)
    }
}