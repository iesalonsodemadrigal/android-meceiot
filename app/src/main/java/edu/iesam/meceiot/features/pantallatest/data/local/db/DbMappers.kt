package edu.iesam.meceiot.features.pantallatest.data.local.db

import edu.iesam.meceiot.features.pantallatest.domain.QuestionGame
import java.util.Date

fun QuestiongameEntity.toDomain(): QuestionGame {
    return QuestionGame(
        id = this.id,
        question = this.question,
        urlImage = this.url_imagen,
        option1 = this.respuesta1,
        option2 = this.respuesta2,
        option3 = this.respuesta3,
        option4 = this.respuesta4
    )
}

fun QuestionGame.toEntity(): QuestiongameEntity {
    return QuestiongameEntity(
        id = this.id,
        question = this.question,
        url_imagen = this.urlImage,
        respuesta1 = this.option1,
        respuesta2 = this.option2,
        respuesta3 = this.option3,
        respuesta4 = this.option4,
        date = Date()
    )
}