package edu.iesam.meceiot.features.lorawan.domain


import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class GetInfoLoraWanUseCaseTest {
    @RelaxedMockK
    private lateinit var loraWanRepository: LoraWanRepository
    private lateinit var getInfoLoraWanUseCase: GetInfoLoraWanUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getInfoLoraWanUseCase = GetInfoLoraWanUseCase(loraWanRepository)
    }

    @Test
    fun `cuando el datasource devuelve una lista vacia`() = runBlocking {
        // Given: Configurar el mock
        coEvery { loraWanRepository.getInfoLoraWan() } returns emptyList()

        // When: Ejecutar el caso de uso
        val loraWanInfo = getInfoLoraWanUseCase()

        // Then: Verificar que se llama al datasource una vez y la lista es vacía
        coVerify(exactly = 1) { loraWanRepository.getInfoLoraWan() }
        assert(loraWanInfo.isEmpty())
    }

    @Test
    fun `cuando el datasource devuelve una lista correcta`() = runBlocking {
        // Given: Crear datos esperados y configurar el mock
        val loraWanInfoExpected = listOf(
            LoraWanInfo(
                "Sensor 1",
                "Descripción del sensor 1",
                "https://alfaiot.com/wp-content/uploads/2022/11/LoRaWAN_Logo.svg_.png",
                "descripion1"
            ),
            LoraWanInfo(
                "Sensor 2", "Descripción del sensor 2",
                "https://alfaiot.com/wp-content/uploads/2022/11/LoRaWAN_Logo.svg_.png)",
                "description2"
            )
        )
        coEvery { loraWanRepository.getInfoLoraWan() } returns loraWanInfoExpected

        // When: Ejecutar el caso de uso
        val loraWanInfoReceived = getInfoLoraWanUseCase()

        // Then: Verificar que se llama al datasource una vez y los datos recibidos son los esperados
        coVerify(exactly = 1) { loraWanRepository.getInfoLoraWan() }
        Assert.assertEquals(loraWanInfoReceived, loraWanInfoExpected)
    }
}