package edu.iesam.meceiot.features.developer.domain.models


data class DeveloperInfo(

    val id: String,

    val name: String,

    val description: String,

    val image: String
)

data class DevelopersResponse(
    val results: List<DeveloperListItem>
)

data class DeveloperListItem(
    val name: String,
    val url: String
)

