package edu.iesam.meceiot.features.developer.data.remote


import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo


class DeveloperApiRemoteDataSource(private val developerApiService: DeveloperApiService) {

    suspend fun getDevelopers(): List<DeveloperInfo> {
        val response = developerApiService.getDevelopers()
        return if (response.isSuccessful) {
            response.body()!!.map {
                it.toModel()
            }
        } else {
            emptyList()
        }
    }

}



