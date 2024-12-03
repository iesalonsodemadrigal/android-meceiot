package edu.iesam.meceiot.features.pantallatest.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

const val QUESTION_TABLE = "question"

@Entity(tableName = QUESTION_TABLE)
data class QuestionEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "text") val text: String,
    @ColumnInfo(name = "urlimagen") val urlimagen: String,
    @ColumnInfo(name = "option1") val option1: String,
    @ColumnInfo(name = "option2") val option2: String,
    @ColumnInfo(name = "option3") val option3: String,
    @ColumnInfo(name = "option4") val option4: String,
    @ColumnInfo(name = "correct_option") val correctOption: String,
    @ColumnInfo(name = "last_date") val date: Date
)