package edu.iesam.meceiot.features.pantallatest.data


class OptionsRepository(private val dataSource: OptionsDataSource) {
    fun getSelectedOptions(): List<QuestionOption> {
        return dataSource.getSelectedOptions()
    }

    fun updateSelectedOption(questionOption: QuestionOption) {
        dataSource.updateSelectedOption(questionOption)
    }
}