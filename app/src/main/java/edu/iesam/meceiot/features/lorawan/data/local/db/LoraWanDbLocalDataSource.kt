package edu.iesam.meceiot.features.lorawan.data.local.db

import android.util.Log
import edu.iesam.meceiot.core.data.local.db.CacheCheck
import edu.iesam.meceiot.core.domain.ErrorApp
import edu.iesam.meceiot.features.lorawan.domain.LoraWanInfo
import org.koin.core.annotation.Single

const val TIME_LORAWAN_TTL = 60000L

@Single
class LoraWanDbLocalDataSource(
    private val loraWanDao: LoraWanDao
    //,private val cacheCheck: CacheCheck<LoraWanEntity>
) {

    suspend fun getAll(): List<LoraWanInfo> {
        val currentTime = System.currentTimeMillis()
        val loraWanInfoEntities = loraWanDao.getAll()

        val validEntities = loraWanInfoEntities.filter { entity ->
            val timeDifference = currentTime - entity.date.time
            timeDifference <= TIME_LORAWAN_TTL
        }

        //simplemente es para saber que se guarda la hora bien y luego se convierte a Long
        loraWanInfoEntities.forEach { entity ->
            Log.d("@dev", "Esto funciona? Fecha de guardado en db: ${entity.date}")
        }

        //debemos lanzar un error cuando se tarda más del tiempo estipulado (ErrorApp??)
        return validEntities.map { it.toDomain() }
        //val loraWanCache = cacheCheck.execute(TIME_LORAWAN_TTL)
        //return loraWanCache.map { it.toDomain() }
    }

    suspend fun saveAll(loraWanInfo: List<LoraWanInfo>) {
        val loraWanInfoEntities = loraWanInfo.map { it.toEntity() }
        loraWanDao.saveAll(*loraWanInfoEntities.toTypedArray())
    }
}