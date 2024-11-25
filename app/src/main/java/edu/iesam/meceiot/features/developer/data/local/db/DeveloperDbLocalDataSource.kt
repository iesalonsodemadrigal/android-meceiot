package edu.iesam.meceiot.features.developer.data.local.db

import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo
import org.koin.core.annotation.Single

@Single
class DeveloperDbLocalDataSource (
private val developerDao: DeveloperDao,
private val cacheCheck: CacheCheck
) {

    suspend fun getAll(): List<DeveloperInfo> {
        val validEntities = cacheCheck.execute(time = 600000)
        return validEntities.map { it.toDomain() }
    }

    suspend fun saveAll(developerInfo: List<DeveloperInfo>) {
        val developerInfoEntities = developerInfo.map { it.toEntity() }
        developerDao.saveAll(*developerInfoEntities.toTypedArray())
    }
}
