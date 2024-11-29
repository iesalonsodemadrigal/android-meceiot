package edu.iesam.meceiot.features.pantallatest.data

import edu.iesam.meceiot.features.pantallatest.domain.Question


class OptionsRepository(private val dataSource: OptionsDataSource) {
    fun getSelectedOptions(): List<Question> {
        return dataSource.getSelectedOptions()
    }

    fun updateSelectedOption(question: Question) {
        dataSource.updateSelectedOption(question)
    }
}