package edu.iesam.meceiot.features.developer.presentation

import android.content.Context
import edu.iesam.meceiot.core.data.api.ApiClient

import edu.iesam.meceiot.features.developer.data.DeveloperDataRepository
import edu.iesam.meceiot.features.developer.data.local.DeveloperXmlLocalDataSource
import edu.iesam.meceiot.features.developer.data.remote.DeveloperApiRemoteDataSource
import edu.iesam.meceiot.features.developer.domain.usecase.GetDevelopersUseCase

class DeveloperFactory(private val context: Context) {

    private val developerApiService = ApiClient.provideInfoDeveloperService()
    private val developerApiRemoteDataSource = DeveloperApiRemoteDataSource(developerApiService)
    private val developerXmlLocalDataSource = DeveloperXmlLocalDataSource(context)
    private val developerDataRepository =
        DeveloperDataRepository(developerXmlLocalDataSource, developerApiRemoteDataSource)
    private val getDevelopersUseCase = GetDevelopersUseCase(developerDataRepository)

    fun provideGetDevelopers(): DeveloperAboutViewModel {
        return DeveloperAboutViewModel(getDevelopersUseCase)
    }
}