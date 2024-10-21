package edu.iesam.meceiot.features.lorawan.presentation

import android.content.Context
import edu.iesam.meceiot.features.lorawan.data.LoraWanDataRepository
import edu.iesam.meceiot.features.lorawan.data.remote.LoraWanMockRemoteDataSource
import edu.iesam.meceiot.features.lorawan.domain.GetInfoLoraWanUseCase

class LoraWanFactory(private val context: Context) {

    private val loraWanMockRemoteDataSource = LoraWanMockRemoteDataSource(context)
    private val loraWanDataRepository = LoraWanDataRepository(loraWanMockRemoteDataSource)
    private val getInfoLoraWanUseCase = GetInfoLoraWanUseCase(loraWanDataRepository)

    fun provideGetInfoLoraWan(): GetInfoLoraWanUseCase {
        return getInfoLoraWanUseCase
    }
}