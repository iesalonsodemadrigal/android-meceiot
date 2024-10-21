package edu.iesam.meceiot.features.lorawan.data

import edu.iesam.meceiot.features.lorawan.data.remote.LoraWanMockRemoteDataSource
import edu.iesam.meceiot.features.lorawan.domain.LoraWanInfo
import edu.iesam.meceiot.features.lorawan.domain.LoraWanRepository

class LoraWanDataRepository(private val loraWanMockRemoteDataSource: LoraWanMockRemoteDataSource) :
    LoraWanRepository {


    override suspend fun getInfoLoraWan(): List<LoraWanInfo> {
        TODO("Not yet implemented")
    }
}