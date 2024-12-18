package edu.iesam.meceiot.features.pantallatest.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.iesam.meceiot.features.pantallatest.data.OptionsDataRepository
import edu.iesam.meceiot.features.pantallatest.domain.Question

class TestViewModel : ViewModel() {
    private val repository = OptionsDataRepository()

    private val _questions = MutableLiveData<List<Question>>()
    val questions: LiveData<List<Question>> get() = _questions

    private val _selectedOptions = MutableLiveData<Map<Int, String>>()
    val selectedOptions: LiveData<Map<Int, String>> get() = _selectedOptions

    private val _correctCount = MutableLiveData<Int>()
    val correctCount: LiveData<Int> get() = _correctCount

    init {
        loadQuestions()
    }

    private fun loadQuestions() {
        _questions.value = repository.getQuestions()
    }

    fun setQuestions(questions: List<Question>) {
        _questions.value = questions
    }

    fun setSelectedOptions(selectedOptions: Map<Int, String>) {
        _selectedOptions.value = selectedOptions
    }

    fun calculateCorrectAnswers() {
        val correctAnswers = _questions.value?.count { question ->
            _selectedOptions.value?.get(question.id) == question.correctOption
        } ?: 0
        _correctCount.value = correctAnswers
    }

    fun saveQuestionToDatabase(question: Question) {
        repository.updateSelectedOption(question)
    }
}