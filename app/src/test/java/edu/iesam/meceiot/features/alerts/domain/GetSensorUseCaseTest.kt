package edu.iesam.meceiot.features.alerts.domain

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetSensorUseCaseTest {

    @RelaxedMockK
    private lateinit var sensorRepository: SensorRepository
    private lateinit var getSensorUseCase: GetSensorUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getSensorUseCase = GetSensorUseCase(sensorRepository)
    }

    @Test
    fun `cuando el sensor no detecta movimiento = 0, o el valor es negativo`() {
        // Give: Declaración de variables
        val mockSensor = listOf(
            Sensor(
                "1",
                "Sensor-22-Hall",
                "Sensor princial", 0
            ),
            Sensor(
                "2",
                "Sensor-Puerta-Salida",
                "Sensor Patio", -1
            )
        )
        coEvery { sensorRepository.getSensors() } returns Result.success(mockSensor)

        // When: Ejecutar el caso de uso
        val result = getSensorUseCase()

        //Then
        assertTrue(result.getOrNull()?.isEmpty() == true)
        assertEquals(0, result.getOrNull()?.size)
    }

    @Test
    fun `cuando el sensor detecta movimiento = 1`() {
        //Given
        val mockSensor = listOf(
            Sensor(
                "1",
                "Sensor-22-Hall",
                "Sensor princial", 1
            ),
            Sensor(
                "2",
                "Sensor-45-Pabellon3",
                "Sensor Pasillo", 1
            )
        )
        coEvery { sensorRepository.getSensors() } returns Result.success(mockSensor)


        //When
        val result = getSensorUseCase()

        //Then
        assertEquals(1, result.getOrNull()?.first()?.movement)

    }

    @Test
    fun `cuando el valor de deteccion es mayor a 1`() {
        //Given
        val mockSensor = listOf(
            Sensor(
                "1",
                "Sensor-22-Hall",
                "Sensor princial", 5
            ),
            Sensor(
                "2",
                "Sensor-45-Pabellon3",
                "Sensor Pasillo", 3
            )
        )
        coEvery { sensorRepository.getSensors() } returns Result.success(mockSensor)


        //Then
        val result = getSensorUseCase()

        //When
        assertTrue(result.getOrNull()?.all { it.movement > 1 } == true)
    }
}