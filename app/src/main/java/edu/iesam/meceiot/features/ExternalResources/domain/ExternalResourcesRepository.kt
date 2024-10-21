package edu.iesam.meceiot.features.ExternalResources.domain

interface ExternalResourcesRepository {
    fun getAllExternalResources(): List<ExtrenalResources>
}