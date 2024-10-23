package edu.iesam.meceiot.features.developer.domain.models

// Model representing a developer
data class
Developer(
    // Unique identifier for the developer
    val id: String,
    // Name of the developer
    val name: String,
    // Description of the developer's role in the project
    val description: String,
    // URL or path to the developer's profile picture
    val image: String
)
