package edu.iesam.meceiot.features.pantallatest.data


class DataSource : OptionsDataSource {
    private val selectedOptions = mutableListOf<QuestionOption>()
    private val correctOptions = listOf(
        QuestionOption(1, "", "a"),
        QuestionOption(2, "", "b"),
        QuestionOption(3, "", "c"),
        QuestionOption(4, "", "a"),
        QuestionOption(5, "", "d"),
        QuestionOption(6, "", "c"),
        QuestionOption(7, "", "b")
    )

    override fun getSelectedOptions(): List<QuestionOption> {
        return selectedOptions
    }

    override fun updateSelectedOption(questionOption: QuestionOption) {
        selectedOptions.removeAll { it.questionId == questionOption.questionId }
        selectedOptions.add(questionOption)
    }

    fun getCorrectOptions(): List<QuestionOption> {
        return correctOptions
    }
}