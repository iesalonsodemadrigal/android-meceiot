package edu.iesam.meceiot.features.lorawan.domain

import org.koin.core.annotation.Single

@Single
class GetInfoLoraWanUseCase(private val loraWanRepository: LoraWanRepository) {

    suspend operator fun invoke(): List<LoraWanInfo>{
        return loraWanRepository.getInfoLoraWan()
    }
}