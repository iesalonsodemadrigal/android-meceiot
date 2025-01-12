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
                title = "Información",
                image = "https://example.com/image1.png",
                description = "informacion relacionada con el centro como historia, localizacion, Contacto..."
            ),
            IoT(
                id = "2",
                title = "Planes Educativos",
                image = "https://example.com/image2.png",
                description = "planes educativos,programacion general anual,reglamento interno"
            ),
            IoT(
                id = "3",
                title = "Calendario Escolar",
                image = "https://example.com/image3.png",
                description = "inicio y fin del curso , dias lectivos y dias no lectivos"
            ),
            IoT(
                id = "4",
                title = "programas",
                image = "https://example.com/image4.png",
                description = "erasmus,FpEmpresa"
            ),
            IoT(
                id = "5",
                title = "departamentos",
                image = "https://example.com/image4.png",
                description = "departamentos del centro con informacion sobre los profesores que lo integran"
            )
        )
    }
}

