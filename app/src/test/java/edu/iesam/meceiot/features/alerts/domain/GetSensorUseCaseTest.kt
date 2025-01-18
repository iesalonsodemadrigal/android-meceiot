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
                        "Sensor princial", TypeSensor.Movement, "-1"
                    ),
                    Sensor(
                        "2",
                        "Sensor-Puerta-Salida",
                        "Sensor Patio", TypeSensor.Movement, "0"
                    )
                )
            )
        )
        val sensor = mockZone.flatMap { it.sensors }
        coEvery { sensorRepository.getSensors() } returns Result.success(sensor)

        // When: Ejecutar el caso de uso
        val result = getSensorUseCase()

        //Then
        val sensors = result.getOrNull()
        assertTrue(sensors.isNullOrEmpty())
    }

    @Test
    fun `cuando el sensor detecta movimiento = 1`() {
        //Given
        val mockZone = listOf(
            Zone(
                "1", "Meceiot_Alonson_Co2_45", listOf(
                    Sensor(
                        "1",
                        "Sensor-43-Pasillo",
                        "Sensor Pabellón A", TypeSensor.Movement, "1"
                    ),
                    Sensor(
                        "2",
                        "Sensor-Entrada",
                        "Sensor Hall", TypeSensor.Movement, "1"
                    )
                )
            )
        )
        val sensor = mockZone.flatMap { it.sensors }
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
    fun `cuando el valor de deteccion es mayor a 1`() {
        //Given
        val mockZone = listOf(
            Zone(
                "1", "Meceiot_Alonson_Temp_13", listOf(
                    Sensor(
                        "1",
                        "Sensor-22-Hall",
                        "Sensor princial", TypeSensor.Movement, "7"
                    ),
                    Sensor(
                        "2",
                        "Sensor-Puerta-Salida",
                        "Sensor Patio", TypeSensor.Movement, "12"
                    )
                )
            )
        )
        val sensor = mockZone.flatMap { it.sensors }
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