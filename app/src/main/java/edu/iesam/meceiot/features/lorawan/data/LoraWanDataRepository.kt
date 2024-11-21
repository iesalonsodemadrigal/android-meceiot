package edu.iesam.meceiot.features.lorawan.data


import edu.iesam.meceiot.features.lorawan.data.local.LoraWanXmlLocalDataSource
import edu.iesam.meceiot.features.lorawan.data.local.db.LoraWanDbLocalDataSource
import edu.iesam.meceiot.features.lorawan.data.remote.LoraWanApiRemoteDataSource
import edu.iesam.meceiot.features.lorawan.domain.LoraWanInfo
import edu.iesam.meceiot.features.lorawan.domain.LoraWanRepository
import org.koin.core.annotation.Single

@Single
class LoraWanDataRepository(
    private val loraWanApiRemoteDataSource: LoraWanApiRemoteDataSource,
    private val loraWanXmlLocalDataSource: LoraWanXmlLocalDataSource,
//    private val loraWanDbLocalDataSource: LoraWanDbLocalDataSource
) : LoraWanRepository {

    override suspend fun getInfoLoraWan(): Result<List<LoraWanInfo>> {
        val loraWanInfoFromLocal = loraWanXmlLocalDataSource.getLoraWanInfo()

        return if (loraWanInfoFromLocal.isEmpty()) {
            loraWanApiRemoteDataSource.getInfoLoraWan().onSuccess {
                loraWanXmlLocalDataSource.saveAll(it)
            }
        } else {
            Result.success(loraWanInfoFromLocal)
        }
    }
}