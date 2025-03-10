package edu.iesam.meceiot.features.externalresources.domain

data class ExternalResources(
    val idExternalResources: String,
    val author: String,
    val description: String,
    val image: String,
    val url: String
)