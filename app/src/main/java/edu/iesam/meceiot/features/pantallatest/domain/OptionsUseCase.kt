package edu.iesam.meceiot.features.pantallatest.domain

import edu.iesam.meceiot.features.pantallatest.data.OptionsRepository
import edu.iesam.meceiot.features.pantallatest.data.QuestionOption

class GetSelectedOptionsUseCase(private val repository: OptionsRepository) {
    suspend fun execute(): List<QuestionOption> {
        return repository.getSelectedOptions()
    }
}

class UpdateSelectedOptionUseCase(private val repository: OptionsRepository) {
    suspend fun execute(questionOption: QuestionOption) {
        repository.updateSelectedOption(questionOption)
    }
}