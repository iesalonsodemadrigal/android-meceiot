package edu.iesam.meceiot.features.pantallatest.data.local.db


import edu.iesam.meceiot.features.pantallatest.domain.Question
import java.util.Date


fun Question.toEntity(): QuestionEntity {
    return QuestionEntity(
        id = this.id,
        text = this.text,
        urlimagen = this.urlimagen,
        option1 = this.option1,
        option2 = this.option2,
        option3 = this.option3,
        option4 = this.option4,
        correctOption = this.correctOption,
        date = Date()
    )
}

fun QuestionEntity.toDomain(): Question {
    return Question(
        id = this.id,
        text = this.text,
        urlimagen = this.urlimagen,
        option1 = this.option1,
        option2 = this.option2,
        option3 = this.option3,
        option4 = this.option4,
        correctOption = this.correctOption
    )
}