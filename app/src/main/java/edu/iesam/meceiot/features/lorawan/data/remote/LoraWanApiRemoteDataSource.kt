package edu.iesam.meceiot.features.lorawan.data.remote

import edu.iesam.meceiot.features.lorawan.domain.LoraWanInfo

class LoraWanApiRemoteDataSource(private val loraWanApiService: LoraWanApiService) {

    suspend fun getInfoLoraWan(): List<LoraWanInfo> {
        val response = loraWanApiService.getLoraWanInfo()
        return if (response.isSuccessful) {
            response.body()!!.map { it.toModel() }
        } else {
            emptyList()
        }
    }

}