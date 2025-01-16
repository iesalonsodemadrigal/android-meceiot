package edu.iesam.meceiot.features.alerts.domain

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
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
    fun `cuando el valor del sensor es 0 o negativo, no detecta movimiento`() {
        // Give: Declaración de variables
        val mockZone = listOf(
            Zone(
                "1", "Meceiot_Alonson_Sound_22", listOf(
                    Sensor(
                        "1",
                        "Sensor-22-Hall",
                        "Sensor princial", "mov", "-1"
                    ),
                    Sensor(
                        "2",
                        "Sensor-Puerta-Salida",
                        "Sensor Patio", "mov", "0"
                    )
                )
            )
        )
        coEvery { sensorRepository.getSensors() } returns Result.success(mockZone)

        // When: Ejecutar el caso de uso
        val result = getSensorUseCase()

        //Then
        val sensors = result.getOrNull()?.flatMap { it.sensors }
        assertTrue(sensors.isNullOrEmpty())
    }

    @Test
    fun `cuando el sensor detecta movimiento = 1`() {
        //Given
        val mockSensor = listOf(
            Zone(
                "1", "Meceiot_Alonson_Co2_45", listOf(
                    Sensor(
                        "1",
                        "Sensor-43-Pasillo",
                        "Sensor Pabellón A", "mov", "1"
                    ),
                    Sensor(
                        "2",
                        "Sensor-Entrada",
                        "Sensor Hall", "mov", "1"
                    )
                )
            )
        )
        coEvery { sensorRepository.getSensors() } returns Result.success(mockSensor)


        //When
        val result = getSensorUseCase()

        //Then
        val sensors = result.getOrNull()?.flatMap { it.sensors }
        sensors?.forEach { sensor ->
            assertTrue(sensor.value.toInt() == 1)
        }

    }

    @Test
    fun `cuando el valor de deteccion es mayor a 1`() {
        //Given
        val mockSensor = listOf(
            Zone(
                "1", "Meceiot_Alonson_Temp_13", listOf(
                    Sensor(
                        "1",
                        "Sensor-22-Hall",
                        "Sensor princial", "mov", "7"
                    ),
                    Sensor(
                        "2",
                        "Sensor-Puerta-Salida",
                        "Sensor Patio", "mov", "12"
                    )
                )
            )
        )
        coEvery { sensorRepository.getSensors() } returns Result.success(mockSensor)


        //When
        val result = getSensorUseCase()

        //Then
        val sensors = result.getOrNull()?.flatMap { it.sensors }
        sensors?.forEach { sensor ->
            assertTrue(sensor.value.toInt() >= 1)
        }
    }
}