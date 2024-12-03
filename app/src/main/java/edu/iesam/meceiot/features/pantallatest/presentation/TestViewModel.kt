package edu.iesam.meceiot.features.pantallatest.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.iesam.meceiot.features.pantallatest.domain.Question

class TestViewModel : ViewModel() {

    private val _questions = MutableLiveData<List<Question>>()
    val questions: LiveData<List<Question>> get() = _questions

    private val _correctCount = MutableLiveData<Int>()
    val correctCount: LiveData<Int> get() = _correctCount

    fun setQuestions(questions: List<Question>) {
        _questions.value = questions
    }

    fun calculateCorrectAnswers(selectedOptions: Map<String, String>) {
        val questions = _questions.value ?: return
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