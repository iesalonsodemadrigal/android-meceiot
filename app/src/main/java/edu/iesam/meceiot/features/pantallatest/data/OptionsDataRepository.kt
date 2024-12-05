package edu.iesam.meceiot.features.pantallatest.data

import edu.iesam.meceiot.features.pantallatest.domain.Question

class OptionsDataRepository {
    private val questions = mutableListOf<Question>()

    fun getSelectedOptions(): List<Question> {
        return questions
    }

    fun updateSelectedOption(question: Question) {
        val index = questions.indexOfFirst { it.id == question.id }
        if (index != -1) {
            questions[index] = question
        } else {
            questions.add(question)
        }
    }
}