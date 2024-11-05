package edu.iesam.meceiot.features.externalresources.data.remote

import edu.iesam.meceiot.features.externalresources.domain.ExternalResources

class ExternalResourcesMockRemoteDataSource {
    private val externalResources = listOf(
        ExternalResources("Nombre 1", "Descripción 1", "https://ejemplo.com/imagen1.jpg"),
        ExternalResources("Nombre 2", "Descripción 2", "https://ejemplo.com/imagen2.jpg"),
        ExternalResources("Nombre 3", "Descripción 3", "https://ejemplo.com/imagen3.jpg")
    )
    fun getAll(): List<ExternalResources> {
        return externalResources
    }

}