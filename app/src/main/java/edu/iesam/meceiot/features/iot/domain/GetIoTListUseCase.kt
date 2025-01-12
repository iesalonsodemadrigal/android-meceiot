package edu.iesam.meceiot.features.iot.domain

import org.koin.core.annotation.Single

@Single
class GetIoTListUseCase(private val repository: IotRepository) {
    operator fun invoke (): List<IoT> {
        return repository.getIoTList()
    }
}
