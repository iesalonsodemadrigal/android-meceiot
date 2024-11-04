package edu.iesam.meceiot.features.externalresources.data

import edu.iesam.meceiot.features.externalresources.data.local.ExternalResourcesXmlLocalDataSource
import edu.iesam.meceiot.features.externalresources.data.remote.ExternalResourcesMockRemoteDataSource
import edu.iesam.meceiot.features.externalresources.domain.ExternalResourcesRepository
import edu.iesam.meceiot.features.externalresources.domain.ExtrenalResources

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
        return local.findAll()
    }
}



