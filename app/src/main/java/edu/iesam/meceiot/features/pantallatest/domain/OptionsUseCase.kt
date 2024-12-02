package edu.iesam.meceiot.features.pantallatest.domain


import Question
import edu.iesam.meceiot.features.pantallatest.data.OptionsRepository

class GetSelectedOptionsUseCase(private val repository: OptionsRepository) {
    fun execute(): List<Question> {
        return repository.getSelectedOptions()
    }
}

class UpdateSelectedOptionUseCase(private val repository: OptionsRepository) {
    fun execute(question: Question) {
        repository.updateSelectedOption(question)
    }
}