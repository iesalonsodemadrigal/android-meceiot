package edu.iesam.meceiot.features.lorawan.data


import android.util.Log
import edu.iesam.meceiot.core.domain.ErrorApp
import edu.iesam.meceiot.features.lorawan.data.local.db.LoraWanDbLocalDataSource
import edu.iesam.meceiot.features.lorawan.data.local.xml.LoraWanXmlLocalDataSource
import edu.iesam.meceiot.features.lorawan.data.remote.LoraWanApiRemoteDataSource
import edu.iesam.meceiot.features.lorawan.domain.LoraWanInfo
import edu.iesam.meceiot.features.lorawan.domain.LoraWanRepository
import org.koin.core.annotation.Single

@Single
class LoraWanDataRepository(
    private val loraWanApiRemoteDataSource: LoraWanApiRemoteDataSource,
    private val loraWanXmlLocalDataSource: LoraWanXmlLocalDataSource,
    private val loraWanDbLocalDataSource: LoraWanDbLocalDataSource
) : LoraWanRepository {

    override suspend fun getInfoLoraWan(): Result<List<LoraWanInfo>> {
        val loraWanInfoFromDbLocal = loraWanDbLocalDataSource.getAll()
        return if (loraWanInfoFromDbLocal.isEmpty()) {
            val loraWanInfoFromXmlLocal = loraWanXmlLocalDataSource.getLoraWanInfo()
            val error = Result.failure<ErrorApp>(ErrorApp.DataErrorApp)
            //lanzamos un error de DataExpiredError si los datos están caducados
            Log.d("@error","$error")
            //no se si la gestión de error de datos expirados va en esta clase o en DbLocalDataSource
            return if (loraWanInfoFromXmlLocal.isEmpty()) {
                loraWanApiRemoteDataSource.getInfoLoraWan().onSuccess {
                    loraWanDbLocalDataSource.saveAll(it)
                    loraWanXmlLocalDataSource.saveAll(it)
                }
            } else {
                loraWanDbLocalDataSource.saveAll(loraWanInfoFromXmlLocal)
                Result.success(loraWanInfoFromXmlLocal)
            }
        } else {
            Result.success(loraWanInfoFromDbLocal)
        }
    }
}