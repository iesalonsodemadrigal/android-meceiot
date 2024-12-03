package edu.iesam.meceiot.features.pantallatest.data.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question_game")
data class QuestionGame(
    @PrimaryKey val id: String,
    val question: String,
    val urlImage: String,
    val option1: String,
    val option2: String,
    val option3: String,
    val option4: String
)