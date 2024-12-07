package edu.iesam.meceiot.features.lorawan.data


import edu.iesam.meceiot.features.lorawan.data.local.db.LoraWanDbLocalDataSource
import edu.iesam.meceiot.features.lorawan.data.remote.LoraWanApiRemoteDataSource
import edu.iesam.meceiot.features.lorawan.domain.LoraWanInfo
import edu.iesam.meceiot.features.lorawan.domain.LoraWanRepository
import org.koin.core.annotation.Single

@Single
class LoraWanDataRepository(
    private val loraWanApiRemoteDataSource: LoraWanApiRemoteDataSource,
    private val loraWanDbLocalDataSource: LoraWanDbLocalDataSource
) : LoraWanRepository {

    override suspend fun getInfoLoraWan(): Result<List<LoraWanInfo>> {
        val loraWanInfoFromDbLocal = loraWanDbLocalDataSource.getAll()

        return if (loraWanInfoFromDbLocal.isFailure) {
                loraWanApiRemoteDataSource.getInfoLoraWan().onSuccess {
                    loraWanDbLocalDataSource.saveAll(it)
            }
        } else {
            Result.success(loraWanInfoFromDbLocal.getOrNull() ?: emptyList())
        }
    }
}