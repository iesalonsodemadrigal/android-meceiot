package edu.iesam.meceiot.features.pantallatest.data

import edu.iesam.meceiot.features.pantallatest.domain.Question

class OptionsDataRepository : OptionsDataSource {
    private val questions = mutableListOf<Question>()

    override fun getSelectedOptions(): List<Question> {
        return questions
    }

    override fun updateSelectedOption(question: Question) {
        val index = questions.indexOfFirst { it.id == question.id }
        if (index != -1) {
            questions[index] = question
        } else {
            questions.add(question)
        }
    }
}