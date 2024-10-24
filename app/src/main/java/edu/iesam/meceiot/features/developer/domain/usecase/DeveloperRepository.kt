package edu.iesam.meceiot.features.developer.domain.usecase

import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo


interface DeveloperRepository {

    // Method that returns a list of developers
    suspend fun getDevelopers(): List<DeveloperInfo>


}