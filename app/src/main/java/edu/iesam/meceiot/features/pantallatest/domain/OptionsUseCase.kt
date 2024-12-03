package edu.iesam.meceiot.features.pantallatest.domain

class OptionsUseCase(private val questionRepository: QuestionRepository) {

    suspend fun invoke(): Result<List<Question>> {
        return questionRepository.getQuestion()
    }
}
