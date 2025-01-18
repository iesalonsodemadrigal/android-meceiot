package edu.iesam.meceiot.features.sensorPanels.domain

import edu.iesam.meceiot.features.sensorpanels.domain.GetSensorPanelsUseCase
import edu.iesam.meceiot.features.sensorpanels.domain.Panel
import edu.iesam.meceiot.features.sensorpanels.domain.Sensor
import edu.iesam.meceiot.features.sensorpanels.domain.SensorPanelRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetSensorPanelsUseCaseTest {
    @RelaxedMockK
    private lateinit var sensorPanelRepository: SensorPanelRepository
    private lateinit var getSensorPanelsUseCase: GetSensorPanelsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getSensorPanelsUseCase = GetSensorPanelsUseCase(sensorPanelRepository)
    }

    @Test
    fun `invoke with EmptyList`() = runBlocking {
        // Given:
        coEvery { sensorPanelRepository.getSensorPanels() } returns Result.success(emptyList())

        // When:
        val sensorPanels = getSensorPanelsUseCase()

        // Then:
        coVerify(exactly = 1) { sensorPanelRepository.getSensorPanels() }
        assert(sensorPanels.isSuccess)
        assert(sensorPanels.getOrNull()?.isEmpty() == true)
    }

    @Test
    fun `invoke with valid list`() = runBlocking {
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

        // When:
        val sensorPanelsReceived = getSensorPanelsUseCase()

        // Then:
        coVerify(exactly = 1) { sensorPanelRepository.getSensorPanels() }
        assert(sensorPanelsReceived.isSuccess)
        assert(sensorPanelsReceived.getOrNull() == sensorPanelsExpected)
    }

    @Test
    fun `invoke with error`() = runBlocking {
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

}