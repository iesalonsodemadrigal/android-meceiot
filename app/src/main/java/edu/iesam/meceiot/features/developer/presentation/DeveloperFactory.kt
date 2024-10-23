package edu.iesam.meceiot.features.developer.presentation

import android.content.Context
import edu.iesam.meceiot.features.developer.data.DeveloperDataRepository
import edu.iesam.meceiot.features.developer.data.remote.DeveloperMockRemoteDataSource
import edu.iesam.meceiot.features.developer.domain.usecase.GetDevelopersUseCase

class DeveloperFactory ( private val context: Context) {
    private val developerMockRemoteDataSource = DeveloperMockRemoteDataSource(context)
    private val developerDataRepository = DeveloperDataRepository(developerMockRemoteDataSource)
    private val getDevelopersUseCase = GetDevelopersUseCase(developerDataRepository)

    fun provideGetDevelopersUseCase(): DeveloperViewModel {
        return DeveloperViewModel(getDevelopersUseCase)
    }
}