package edu.iesam.meceiot.features.IoT.domain

interface IotRepository {

    suspend fun getInfoIoT(): Result<List<IoT>>
}