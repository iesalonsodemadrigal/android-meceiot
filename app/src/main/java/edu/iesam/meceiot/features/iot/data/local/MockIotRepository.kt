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
                title = "IoT en el Alonso",
                image = "https://example.com/image1.png",
                description = "Uso del IoT en las instalaciones del Alonso..."
            ),
            IoT(
                id = "2",
                title = "Ahorro energético",
                image = "https://example.com/image2.png",
                description = "El IoT puede optimizar el consumo energético..."
            ),
            IoT(
                id = "3",
                title = "MecelOT",
                image = "https://example.com/image3.png",
                description = "Plataforma de IoT para industrias inteligentes..."
            ),
            IoT(
                id = "4",
                title = "Contextos de IoT",
                image = "https://example.com/image4.png",
                description = "Áreas donde el IoT se utiliza con mayor frecuencia..."
            )
        )
    }
}

