package edu.iesam.meceiot.features.pantallatest.data

data class Question(
    val id: Int,
    val text: String,
    val imageResId: Int,
    val options: List<String>,
    val correctOption: String
)