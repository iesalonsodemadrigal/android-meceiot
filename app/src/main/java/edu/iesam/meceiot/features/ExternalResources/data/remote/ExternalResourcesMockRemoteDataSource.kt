package edu.iesam.meceiot.features.ExternalResources.data.remote

import edu.iesam.meceiot.features.ExternalResources.domain.ExtrenalResources

class ExternalResourcesMockRemoteDataSource {
    private val externalResources = listOf(
        ExtrenalResources("Nombre 1", "Descripción 1", "https://ejemplo.com/imagen1.jpg"),
        ExtrenalResources("Nombre 2", "Descripción 2", "https://ejemplo.com/imagen2.jpg"),
        ExtrenalResources("Nombre 3", "Descripción 3", "https://ejemplo.com/imagen3.jpg")
    )
    fun getAll(): List<ExtrenalResources> {
        return externalResources
    }

}