package edu.iesam.meceiot.features.developer.domain.usecase

import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo


// Use case to retrieve a list of developers from the repository
class GetDevelopersUseCase(private val developerRepository: DeveloperRepository) {

    // Invokes the use case to get developers
    suspend operator fun invoke(): List<DeveloperInfo> {
        // Calls the repository method to fetch the list of developers
        return developerRepository.getDevelopers()
    }


}