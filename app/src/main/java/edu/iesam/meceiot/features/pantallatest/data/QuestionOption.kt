package edu.iesam.meceiot.features.pantallatest.data

data class QuestionOption(val questionId: Int, val option: String , val correctOption: String)

interface OptionsDataSource {
    fun getSelectedOptions(): List<QuestionOption>
    fun updateSelectedOption(questionOption: QuestionOption)
}