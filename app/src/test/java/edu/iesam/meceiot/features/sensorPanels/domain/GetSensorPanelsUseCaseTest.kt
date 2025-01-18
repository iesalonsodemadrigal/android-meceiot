package edu.iesam.meceiot.features.sensorPanels.domain

import edu.iesam.meceiot.features.sensorpanels.domain.GetSensorPanelsUseCase
import edu.iesam.meceiot.features.sensorpanels.domain.Panel
import edu.iesam.meceiot.features.sensorpanels.domain.Sensor
import edu.iesam.meceiot.features.sensorpanels.domain.SensorPanelRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.IOException

class GetSensorPanelsUseCaseTest {
    private var sensorPanelRepository: SensorPanelRepository = mockk()
    private var getSensorPanelsUseCase = GetSensorPanelsUseCase(sensorPanelRepository)

    @Test
    fun `invoke should return empty list when repository returns empty list`() = runTest {
        // Given
        coEvery { sensorPanelRepository.getSensorPanels() } returns Result.success(emptyList())

        // When
        val result = getSensorPanelsUseCase()

        // Then
        coVerify(exactly = 1) { sensorPanelRepository.getSensorPanels() }
        assertEquals(Result.success(emptyList<Panel>()), result)
    }

    @Test
    fun `invoke should return panel list when repository succeeds`() = runTest {
        // Given:
        val sensorPanelsExpected = listOf(
            Panel(
                id = 1,
                name = "Panel 1",
                sensors = listOf(
                    Sensor(
                        id = 1,
                        name = "Temperature"
                    ),
                    Sensor(
                        id = 2,
                        name = "Humidity"
                    ),
                    Sensor(
                        id = 3,
                        name = "CO2"
                    ),
                    Sensor(
                        id = 4,
                        name = "Motion"
                    ),
                    Sensor(
                        id = 5,
                        name = "Sensor 5"
                    )
                )
            ),
            Panel(
                id = 2,
                name = "Panel 2",
                sensors = listOf(
                    Sensor(
                        id = 3,
                        name = "Humidity"
                    ),
                    Sensor(
                        id = 4,
                        name = "CO2"
                    ),
                    Sensor(
                        id = 5,
                        name = "Sensor 5"
                    ),
                )
            ),
            Panel(
                id = 3,
                name = "Panel 3",
                sensors = listOf(
                    Sensor(
                        id = 1,
                        name = "Temperature"
                    )
                )
            ),
        )

        coEvery { sensorPanelRepository.getSensorPanels() } returns Result.success(
            sensorPanelsExpected
        )

        // When
        val result = getSensorPanelsUseCase()

        // Then
        coVerify(exactly = 1) { sensorPanelRepository.getSensorPanels() }
        assertEquals(Result.success(sensorPanelsExpected), result)
    }

    @Test
    fun `invoke with error`() = runTest {
        // Given:
        val exception = Exception("Error fetching sensor panels")
        coEvery { sensorPanelRepository.getSensorPanels() } returns Result.failure(exception)

        // When:
        val sensorPanels = getSensorPanelsUseCase()

        // Then:
        coVerify(exactly = 1) { sensorPanelRepository.getSensorPanels() }
        assert(sensorPanels.isFailure)
        assert(sensorPanels.exceptionOrNull() == exception)
    }

    @Test
    fun `invoke should return Failure with IOException when repository throws IOException`() =
        runTest {
            // Given
            val expectedException = IOException("Network error")
            coEvery { sensorPanelRepository.getSensorPanels() } returns Result.failure(
                expectedException
            )

            // When
            val result = getSensorPanelsUseCase()

            // Then
            coVerify(exactly = 1) { sensorPanelRepository.getSensorPanels() }
            assertEquals(Result.failure<List<Panel>>(expectedException), result)
        }

    @Test
    fun `invoke should return Failure with generic Exception when repository throws Exception`() =
        runTest {
            // Given
            val expectedException = Exception("Generic error")
            coEvery { sensorPanelRepository.getSensorPanels() } returns Result.failure(
                expectedException
            )

            // When
            val result = getSensorPanelsUseCase()

            // Then
            coVerify(exactly = 1) { sensorPanelRepository.getSensorPanels() }
            assertEquals(Result.failure<List<Panel>>(expectedException), result)
        }
}