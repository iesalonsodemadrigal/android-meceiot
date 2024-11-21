package edu.iesam.meceiot.features.lorawan.data.remote

import edu.iesam.meceiot.core.data.remote.apiCall
import edu.iesam.meceiot.features.lorawan.domain.LoraWanInfo
import org.koin.core.annotation.Single

@Single
class LoraWanApiRemoteDataSource(private val loraWanApiService: LoraWanApiService) {

    suspend fun getInfoLoraWan(): Result<List<LoraWanInfo>> {
        return apiCall { loraWanApiService.getLoraWanInfo() }.map { loraWanApiModel ->
            loraWanApiModel.map { it.toModel() }
        }
    }
}