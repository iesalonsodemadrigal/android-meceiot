package edu.iesam.meceiot.features.pantallatest.data.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "questions")
data class QuestionEntity(
    @PrimaryKey val id: Int,
    val Text: String,
    val imageUrl: String,
    val optionA: String,
    val optionB: String,
    val optionC: String,
    val optionD: String,
    val correctOption: String,
    val lastDate: Date
)