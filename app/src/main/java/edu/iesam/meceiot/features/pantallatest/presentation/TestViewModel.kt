package edu.iesam.meceiot.features.pantallatest.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import edu.iesam.meceiot.core.data.local.db.converters.DatabaseProvider
import edu.iesam.meceiot.features.pantallatest.data.local.db.QuestionDblocalDataSource
import edu.iesam.meceiot.features.pantallatest.data.local.db.toEntity
import edu.iesam.meceiot.features.pantallatest.domain.Question
import kotlinx.coroutines.launch

class TestViewModel(application: Application) : AndroidViewModel(application) {

    private val questionDblocalDataSource: QuestionDblocalDataSource =
        DatabaseProvider.provideQuestionDblocalDataSource(application)

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

    fun saveQuestionToDatabase(question: Question) {
        viewModelScope.launch {
            val questionEntity = question.toEntity()
            Log.d("TestViewModel", "Saving question to database: $questionEntity")
            questionDblocalDataSource.insertOrUpdateQuestion(questionEntity)
        }
    }
}