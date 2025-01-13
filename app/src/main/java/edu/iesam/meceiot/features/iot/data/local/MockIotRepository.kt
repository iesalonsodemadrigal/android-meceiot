package edu.iesam.meceiot.features.iot.data.local

import edu.iesam.meceiot.features.iot.domain.IoT
import edu.iesam.meceiot.features.iot.domain.IotRepository
import org.koin.core.annotation.Single

@Single
class MockIoTRepository : IotRepository {
    override fun getIoTList(): List<IoT> {
        return listOf(
            IoT(
                id = "1",
                title = "IoT",
                image = "android.resource://edu.iesam.meceiot/drawable/icon",
                description = "informacion relacionada con IoT..."
            ),
            IoT(
                id = "2",
                title = "Ahorro energetico a través de IoT",
                image = "android.resource://edu.iesam.meceiot/drawable/icon",
                description = "Ahorro energetico..... "
            ),
            IoT(
                id = "3",
                title = "Contextos donde mas se usa IoT",
                image = "android.resource://edu.iesam.meceiot/drawable/icon",
                description = "Iot es mas usado en......"
            ),
            IoT(
                id = "4",
                title = "IoT en el Alonso",
                image = "android.resource://edu.iesam.meceiot/drawable/icon",
                description = "el Ies Alonso de Madrigal tiene IoT en...."
            ),
            IoT(
                id = "5",
                title = "MeceIot",
                image = "android.resource://edu.iesam.meceiot/drawable/icon",
                description = "MeceIot es un proyecto que...."
            )
        )
    }
}

