package edu.iesam.meceiot.features.pantallatest.data

import edu.iesam.meceiot.features.pantallatest.data.local.db.QuestiongameDao
import edu.iesam.meceiot.features.pantallatest.data.local.db.QuestiongameEntity
import java.util.Date

class OptionsRepository(private val questiongameDao: QuestiongameDao) {
    suspend fun getSelectedOptions(): List<QuestionOption> {
        return questiongameDao.getAll().map { entity ->
            QuestionOption(
                questionId = entity.id,
                option = entity.respuesta1,
                correctOption = entity.respuesta1
            )
        }
    }
    suspend fun updateSelectedOption(questionOption: QuestionOption) {
        val entity = QuestiongameEntity(
            id = questionOption.questionId,
            question = "",
            url_imagen = "",
            respuesta1 = questionOption.option,
            respuesta2 = "",
            respuesta3 = "",
            respuesta4 = "",
            date = Date()
        )
        questiongameDao.insert(entity)
    }
}