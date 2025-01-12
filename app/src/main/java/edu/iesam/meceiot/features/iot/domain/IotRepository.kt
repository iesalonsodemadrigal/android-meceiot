package edu.iesam.meceiot.features.iot.domain

interface IotRepository {

    fun getIoTList(): List<IoT>
}