package edu.iesam.meceiot.features.pantallatest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.meceiot.features.pantallatest.data.QuestionGameRepository
import edu.iesam.meceiot.features.pantallatest.domain.QuestionGame
import kotlinx.coroutines.launch

class QuestionGameViewModel(private val repository: QuestionGameRepository) : ViewModel() {

    fun initializeDatabase() {
        viewModelScope.launch {
            val questions = repository.getAllQuestions()
            if (questions.isEmpty()) {
                val exampleQuestions = listOf(
                    QuestionGame("1", "Question 1", "url1", "Option 1", "Option 2", "Option 3", "Option 4"),
                    QuestionGame("2", "Question 2", "url2", "Option 1", "Option 2", "Option 3", "Option 4")
                )
                repository.insertQuestions(*exampleQuestions.toTypedArray())
            }
        }
    }
}