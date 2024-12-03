package edu.iesam.meceiot.features.pantallatest.data

import edu.iesam.meceiot.features.pantallatest.data.local.db.QuestionDbLocalDataSource
import edu.iesam.meceiot.features.pantallatest.domain.Question
import edu.iesam.meceiot.features.pantallatest.domain.QuestionRepository
import org.koin.core.annotation.Single

@Single
class QuestionDataRepository(
    private val questionDbLocalDataSource: QuestionDbLocalDataSource
) : QuestionRepository {

    override suspend fun getQuestion(): Result<List<Question>> {
        val questionInfoFromDbLocal = questionDbLocalDataSource.getAll()
        return if (questionInfoFromDbLocal.isEmpty()) {
            val generatedQuestions = generateQuestions()
            questionDbLocalDataSource.saveAll(generatedQuestions)
            Result.success(generatedQuestions)
        } else {
            Result.success(questionInfoFromDbLocal)
        }
    }

    private fun generateQuestions(): List<Question> {
        return listOf(
            Question(
                "1",
                "Sample Question 1",
                "url1",
                "Option 1",
                "Option 2",
                "Option 3",
                "Option 4",
                "Option 1"
            ),
            Question(
                "2",
                "Sample Question 2",
                "url2",
                "Option 1",
                "Option 2",
                "Option 3",
                "Option 4",
                "Option 2"
            )
        )
    }
}