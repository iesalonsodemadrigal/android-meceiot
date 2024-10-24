package edu.iesam.meceiot.features.developer.domain.usecase

import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetDevelopersUseCaseTest {

    @BeforeEach
    fun setUp() {

    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    operator fun invoke() {
        val developerRepository = mockk<DeveloperRepository>()
        val useCase = GetDevelopersUseCase(developerRepository)
    }
}