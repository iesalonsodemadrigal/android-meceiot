package edu.iesam.meceiot.features.externalresources.presentation

import android.content.Context
import edu.iesam.meceiot.core.data.ApiClient
import edu.iesam.meceiot.features.externalresources.data.ExternalResourcesDataRepsitory
import edu.iesam.meceiot.features.externalresources.data.local.ExternalResourcesXmlLocalDataSource
import edu.iesam.meceiot.features.externalresources.data.remote.ExternalResourcesRemoteDataSource
import edu.iesam.meceiot.features.externalresources.domain.GetAllExternalResourcesUseCase

class ResourceFactory (private val context: Context){
    val resourcesXmlLocal = ExternalResourcesXmlLocalDataSource(context)

    val resourceDataRepository = ExternalResourcesDataRepsitory(
        getResourceApiDataSource(), resourcesXmlLocal)

    val getResourcesUseCase = GetAllExternalResourcesUseCase(resourceDataRepository)


    fun getResourcesViewModel(): ExternalResourcesViewModel {

        return ExternalResourcesViewModel(getResourcesUseCase)

    }

    fun getResourceApiDataSource(): ExternalResourcesRemoteDataSource {
        val resourceService = ApiClient.provideExternalResourcesService()
        return ExternalResourcesRemoteDataSource(resourceService)
    }
}

