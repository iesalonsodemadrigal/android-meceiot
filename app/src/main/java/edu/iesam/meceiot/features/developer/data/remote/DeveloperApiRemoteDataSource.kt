package edu.iesam.meceiot.features.developer.data.remote

import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeveloperApiRemoteDataSource(private val developerApiService: DeveloperApiService) {


    suspend fun getDevelopers(limit: Int = 50, offset: Int = 0): List<DeveloperInfo> {

        return withContext(Dispatchers.IO) {


        }


    }


}