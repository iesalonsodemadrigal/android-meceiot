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
                image = "android.resource://edu.iesam.meceiot/drawable/icon",
                description = "informacion relacionada con el centro como historia, localizacion, Contacto..."
            ),
            IoT(
                id = "2",
                title = "Planes Educativos",
                image = "android.resource://edu.iesam.meceiot/drawable/icon",
                description = "planes educativos,programacion general anual,reglamento interno"
            ),
            IoT(
                id = "3",
                title = "Calendario Escolar",
                image = "android.resource://edu.iesam.meceiot/drawable/icon",
                description = "inicio y fin del curso , dias lectivos y dias no lectivos"
            ),
            IoT(
                id = "4",
                title = "programas",
                image = "android.resource://edu.iesam.meceiot/drawable/icon",
                description = "erasmus,FpEmpresa"
            ),
            IoT(
                id = "5",
                title = "departamentos",
                image = "android.resource://edu.iesam.meceiot/drawable/icon",
                description = "departamentos del centro con informacion sobre los profesores que lo integran"
            )
        )
    }
}

