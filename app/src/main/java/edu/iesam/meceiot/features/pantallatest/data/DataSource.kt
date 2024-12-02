package edu.iesam.meceiot.features.pantallatest.data

import Question


interface OptionsDataSource {
    fun getSelectedOptions(): List<Question>
    fun updateSelectedOption(question: Question)
}