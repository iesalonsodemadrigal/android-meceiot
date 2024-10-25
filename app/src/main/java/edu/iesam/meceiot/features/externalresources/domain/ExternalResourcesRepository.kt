package edu.iesam.meceiot.features.externalresources.domain

interface ExternalResourcesRepository {
    fun getAllExternalResources(): List<ExtrenalResources>
}