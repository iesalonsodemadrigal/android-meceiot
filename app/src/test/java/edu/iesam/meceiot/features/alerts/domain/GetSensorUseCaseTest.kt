package edu.iesam.meceiot.features.alerts.domain

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
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
    fun `cuando el valor del sensor es 0 o negativo, no detecta movimiento`() = runBlocking {
        // Give: Declaración de variables
        val mockPanel = listOf(
            Panel(
                "1", "Meceiot_Alonson_Sound_22", listOf(
                    Sensor(
                        "1",
                        "Sensor-22-Hall", TypeSensor.Movement, "-1"
                    ),
                    Sensor(
                        "2",
                        "Sensor-Puerta-Salida", TypeSensor.Movement, "0"
                    )
                )
            )
        )
        val sensor = mockPanel.flatMap { it.sensors }
        coEvery { sensorRepository.getSensors() } returns Result.success(sensor)

        // When: Ejecutar el caso de uso
        val result = getSensorUseCase()

        //Then
        val sensors = result.getOrNull()
        assertTrue(sensors.isNullOrEmpty())
    }

    @Test
    fun `cuando el sensor detecta movimiento = 1`(): Unit = runBlocking {
        //Given
        val mockPanel = listOf(
            Panel(
                "1", "Meceiot_Alonson_Co2_45", listOf(
                    Sensor(
                        "1",
                        "Sensor-43-Pasillo", TypeSensor.Movement, "1"
                    ),
                    Sensor(
                        "2",
                        "Sensor-Entrada", TypeSensor.Movement, "1"
                    )
                )
            )
        )
        val sensor = mockPanel.flatMap { it.sensors }
        coEvery { sensorRepository.getSensors() } returns Result.success(sensor)


        //When
        val result = getSensorUseCase()

        //Then
        val sensors = result.getOrNull()
        sensors?.forEach { sensor ->
            assertTrue(sensor.value.toInt() == 1)
        }

    }

    @Test
    fun `cuando el valor de deteccion es mayor a 1`(): Unit = runBlocking {
        //Given
        val mockPanel = listOf(
            Panel(
                "1", "Meceiot_Alonson_Temp_13", listOf(
                    Sensor(
                        "1",
                        "Sensor-22-Hall", TypeSensor.Movement, "7"
                    ),
                    Sensor(
                        "2",
                        "Sensor-Puerta-Salida", TypeSensor.Movement, "12"
                    )
                )
            )
        )
        val sensor = mockPanel.flatMap { it.sensors }
        coEvery { sensorRepository.getSensors() } returns Result.success(sensor)


        //When
        val result = getSensorUseCase()

        //Then
        val sensors = result.getOrNull()
        sensors?.forEach { sensor ->
            assertTrue(sensor.value.toInt() >= 1)
        }
    }
}