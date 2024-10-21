package edu.iesam.meceiot.features.lorawan.domain

interface LoraWanRepository {

    suspend fun getInfoLoraWan(): List<LoraWanInfo>
}