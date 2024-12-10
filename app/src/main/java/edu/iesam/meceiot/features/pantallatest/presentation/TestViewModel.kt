package edu.iesam.meceiot.features.pantallatest.presentation


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.iesam.meceiot.features.pantallatest.domain.Question


class TestViewModel : ViewModel() {

    private val _questions = MutableLiveData<List<Question>>()
    val questions: LiveData<List<Question>> get() = _questions

    private val _selectedOptions = MutableLiveData<Map<Int, String>>()
    val selectedOptions: LiveData<Map<Int, String>> get() = _selectedOptions

    private val _correctCount = MutableLiveData<Int>()
    val correctCount: LiveData<Int> get() = _correctCount

    fun setQuestions(questions: List<Question>) {
        _questions.value = questions
    }

    fun setSelectedOptions(selectedOptions: Map<Int, String>) {
        _selectedOptions.value = selectedOptions
    }

    fun updateSelectedOption(questionId: Int, selectedOption: String) {
        val currentOptions = _selectedOptions.value?.toMutableMap() ?: mutableMapOf()
        currentOptions[questionId] = selectedOption
        _selectedOptions.value = currentOptions
    }

    fun calculateCorrectAnswers() {
        val questions = _questions.value ?: return
        val selectedOptions = _selectedOptions.value ?: return
        var correctCount = 0
        for ((questionId, selectedOption) in selectedOptions) {
            val correctOption = questions.find { it.id == questionId }?.correctOption
            if (selectedOption == correctOption) {
                correctCount++
            }
        }
        _correctCount.value = correctCount
    }
}