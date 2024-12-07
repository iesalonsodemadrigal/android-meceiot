package edu.iesam.meceiot.features.pantallatest.domain

import edu.iesam.meceiot.features.pantallatest.data.OptionsDataRepository

class GetSelectedOptionsUseCase(private val repository: OptionsDataRepository) {
    fun execute(): List<Question> {
        return repository.getSelectedOptions()
    }
}

class UpdateSelectedOptionUseCase(private val repository: OptionsDataRepository) {
    fun execute(question: Question) {
        repository.updateSelectedOption(question)
    }
}