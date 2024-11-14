package edu.iesam.meceiot.features.lorawan.data

import edu.iesam.meceiot.core.domain.ErrorApp
import edu.iesam.meceiot.features.lorawan.data.local.LoraWanXmlLocalDataSource
import edu.iesam.meceiot.features.lorawan.data.remote.LoraWanApiRemoteDataSource
import edu.iesam.meceiot.features.lorawan.domain.LoraWanInfo
import edu.iesam.meceiot.features.lorawan.domain.LoraWanRepository
import org.koin.core.annotation.Single

@Single
class LoraWanDataRepository(
    private val loraWanApiRemoteDataSource: LoraWanApiRemoteDataSource,
    private val loraWanXmlLocalDataSource: LoraWanXmlLocalDataSource
) : LoraWanRepository {

    override suspend fun getInfoLoraWan(): Result<List<LoraWanInfo>> {
        val loraWanInfoFromLocal = loraWanXmlLocalDataSource.getLoraWanInfo()

        return if (loraWanInfoFromLocal.isEmpty()) {
            val loraWanInfoFromRemote = loraWanApiRemoteDataSource.getInfoLoraWan()
            return loraWanInfoFromRemote.fold(
                onSuccess = { loraWanInfo ->
                    loraWanXmlLocalDataSource.saveAll(loraWanInfo)
                    Result.success(loraWanInfo)
                },
                onFailure = { error ->
                    Result.failure(error as? ErrorApp ?: ErrorApp.DataErrorApp)
                })
        } else {
            Result.success(loraWanInfoFromLocal)
        }
    }
}