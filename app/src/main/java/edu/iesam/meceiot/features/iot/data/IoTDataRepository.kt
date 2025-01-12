package edu.iesam.meceiot.features.iot.data

import edu.iesam.meceiot.features.iot.data.local.MockIoTRepository
import edu.iesam.meceiot.features.iot.domain.IoT
import edu.iesam.meceiot.features.iot.domain.IotRepository
import org.koin.core.annotation.Single

@Single
class IoTDataRepository(private val mockIotRepository: MockIoTRepository) : IotRepository {
    override fun getIoTList(): List<IoT> {
        return mockIotRepository.getIoTList()
    }


}