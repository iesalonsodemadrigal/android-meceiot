package edu.iesam.meceiot.features.ExternalResources.data

import edu.iesam.meceiot.features.ExternalResources.data.local.ExternalResourcesXmlLocalDataSource
import edu.iesam.meceiot.features.ExternalResources.data.remote.ExternalResourcesMockRemoteDataSource
import edu.iesam.meceiot.features.ExternalResources.domain.ExternalResourcesRepository
import edu.iesam.meceiot.features.ExternalResources.domain.ExtrenalResources

class ExternalResourcesDataRepsitory(
    private val local: ExternalResourcesXmlLocalDataSource,
    private val mockRemote: ExternalResourcesMockRemoteDataSource
) :
    ExternalResourcesRepository {
    override fun getAllExternalResources(): List<ExtrenalResources> {
        val externalResources = local.findAll()
        if (externalResources.isEmpty()) {
            val externalResourcesFromRemote = mockRemote.getAll()
            local.saveAll(externalResourcesFromRemote)
        } else
            return externalResources
    }
}


