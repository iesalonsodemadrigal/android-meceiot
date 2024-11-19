package edu.iesam.meceiot.features.pantallatest.domain

data class QuestionGame(
    val id: Int,
    val question: String,
    val urlImage: String,
    val option1: String,
    val option2: String,
    val option3: String,
    val option4: String
)