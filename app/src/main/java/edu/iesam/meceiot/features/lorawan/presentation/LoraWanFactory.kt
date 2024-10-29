package edu.iesam.meceiot.features.lorawan.presentation

import android.content.Context
import edu.iesam.meceiot.core.data.ApiClient
import edu.iesam.meceiot.features.lorawan.data.LoraWanDataRepository
import edu.iesam.meceiot.features.lorawan.data.local.LoraWanXmlLocalDataSource
import edu.iesam.meceiot.features.lorawan.data.remote.LoraWanApiRemoteDataSource
import edu.iesam.meceiot.features.lorawan.domain.GetInfoLoraWanUseCase

class LoraWanFactory(private val context: Context) {

    private val loraWanApiService = ApiClient.provideInfoLorawanService()
    private val loraWanApiRemoteDataSource = LoraWanApiRemoteDataSource(loraWanApiService)
    private val loraWanXmlLocalDataSource = LoraWanXmlLocalDataSource(context)
    private val loraWanDataRepository =
        LoraWanDataRepository(loraWanApiRemoteDataSource, loraWanXmlLocalDataSource)
    private val getInfoLoraWanUseCase = GetInfoLoraWanUseCase(loraWanDataRepository)

    fun provideGetInfoLoraWan(): LoraWanViewModel {
        return LoraWanViewModel(getInfoLoraWanUseCase)
    }
}