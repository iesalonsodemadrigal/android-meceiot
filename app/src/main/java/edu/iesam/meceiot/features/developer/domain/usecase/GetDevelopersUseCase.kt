package edu.iesam.meceiot.features.developer.domain.usecase

import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo


class GetDevelopersUseCase(private val developerRepository: DeveloperRepository) {


    suspend operator fun invoke(): List<DeveloperInfo> {
        return developerRepository.getDevelopers()
    }


}