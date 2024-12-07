package edu.iesam.meceiot.features.developer.data.local.db

import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo
import org.koin.core.annotation.Single

@Single
class DeveloperDbLocalDataSource (
    private val developerDao: DeveloperDao
) {

    suspend fun getAll(): List<DeveloperInfo> {
        return developerDao.getAll().map { it.toDomain() }
    }

    suspend fun saveAll(developerInfo: List<DeveloperInfo>) {
        val developerInfoEntities = developerInfo.map { it.toEntity() }
        developerDao.saveAll(*developerInfoEntities.toTypedArray())
    }
}
