package edu.iesam.meceiot.features.lorawan.domain

class GetInfoLoraWanUseCase(private val loraWanRepository: LoraWanRepository) {

    suspend operator fun invoke(): List<LoraWanInfo>{
        return loraWanRepository.getInfoLoraWan()
    }
}