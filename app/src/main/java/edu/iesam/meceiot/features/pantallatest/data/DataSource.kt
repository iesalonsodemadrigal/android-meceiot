package edu.iesam.meceiot.features.pantallatest.data

interface OptionsDataSource {
    fun getSelectedOptions(): List<QuestionOption>
    fun updateSelectedOption(questionOption: QuestionOption)
}