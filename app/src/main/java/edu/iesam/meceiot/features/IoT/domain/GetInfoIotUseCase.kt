package edu.iesam.meceiot.features.IoT.domain

class GetInfoIotUseCase(private val iotRepository: IotRepository) {

    suspend operator fun invoke(): Result<List<IoT>> {
        return iotRepository.getInfoIoT()
    }
}