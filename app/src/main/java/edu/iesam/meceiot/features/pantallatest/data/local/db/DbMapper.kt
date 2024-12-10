package edu.iesam.meceiot.features.pantallatest.data.local.db

import edu.iesam.meceiot.features.pantallatest.domain.Question
import java.util.Date

fun QuestionEntity.toDomain(): Question =
    Question(
        id = this.id,
        text = this.Text,
        urlimagen = this.imageUrl,
        option1 = this.optionA,
        option2 = this.optionB,
        option3 = this.optionC,
        option4 = this.optionD,
        correctOption = this.correctOption
    )

fun Question.toEntity(): QuestionEntity =
    QuestionEntity(
        id = this.id,
        Text = this.text,
        imageUrl = this.urlimagen,
        optionA = this.option1,
        optionB = this.option2,
        optionC = this.option3,
        optionD = this.option4,
        correctOption = this.correctOption,
        lastDate = Date()
    )