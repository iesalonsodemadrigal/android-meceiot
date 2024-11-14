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
        // Given:
        coEvery { loraWanRepository.getInfoLoraWan() } returns Result.success(emptyList())

        // When:
        val loraWanInfo = getInfoLoraWanUseCase()

        // Then:
        coVerify(exactly = 1) { loraWanRepository.getInfoLoraWan() }
        assert(loraWanInfo.getOrNull()?.isEmpty() == true)
    }

    @Test
    fun `cuando el datasource devuelve una lista correcta`() = runBlocking {
        // Given:
        val loraWanInfoExpected = listOf(
            LoraWanInfo(
                "1",
                "title1",
                "https://alfaiot.com/wp-content/uploads/2022/11/LoRaWAN_Logo.svg_.png",
                "descripion1"
            ),
            LoraWanInfo(
                "2", "title2",
                "https://alfaiot.com/wp-content/uploads/2022/11/LoRaWAN_Logo.svg_.png)",
                "description2"
            )
        )
        coEvery { loraWanRepository.getInfoLoraWan() } returns Result.success(loraWanInfoExpected)

        // When:
        val loraWanInfoReceived = getInfoLoraWanUseCase()

        // Then:
        coVerify(exactly = 1) { loraWanRepository.getInfoLoraWan() }
        Assert.assertEquals(loraWanInfoExpected, loraWanInfoReceived.getOrNull())
    }
}