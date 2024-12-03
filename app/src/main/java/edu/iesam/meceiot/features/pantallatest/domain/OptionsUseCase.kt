package edu.iesam.meceiot.features.pantallatest.domain

import org.koin.core.annotation.Single

@Single
class OptionsUseCase(private val questionRepository: QuestionRepository) {

    suspend fun invoke(): Result<List<Question>> {
        return questionRepository.getQuestion()
    }
}
