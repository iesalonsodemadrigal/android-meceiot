package edu.iesam.meceiot.features.pantallatest.data

import edu.iesam.meceiot.features.pantallatest.data.local.db.QuestiongameDao
import edu.iesam.meceiot.features.pantallatest.data.local.db.QuestiongameEntity
import java.util.Date

class OptionsRepository(private val questiongameDao: QuestiongameDao) {
    suspend fun getSelectedOptions(): List<QuestionOption> {
        return questiongameDao.findAll().map { entity ->
            QuestionOption(
                questionId = entity.id,
                option = entity.respuesta1, // Asumiendo que respuesta1 es la opción seleccionada
                correctOption = entity.respuesta1 // Asumiendo que respuesta1 es la opción correcta
            )
        }
    }

    suspend fun updateSelectedOption(questionOption: QuestionOption) {
        val entity = QuestiongameEntity(
            id = questionOption.questionId,
            question = "", // Proporciona el texto real de la pregunta
            url_imagen = "", // Proporciona la URL de la imagen real
            respuesta1 = questionOption.option,
            respuesta2 = "", // Proporciona la opción2 real
            respuesta3 = "", // Proporciona la opción3 real
            respuesta4 = "", // Proporciona la opción4 real
            date = Date() // Proporciona la fecha real
        )
        questiongameDao.insert(entity)
    }
}