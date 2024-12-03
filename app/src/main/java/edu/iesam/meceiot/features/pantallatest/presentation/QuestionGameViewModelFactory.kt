package edu.iesam.meceiot.features.pantallatest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.iesam.meceiot.features.pantallatest.data.QuestionGameRepository

class QuestionGameViewModelFactory(private val repository: QuestionGameRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuestionGameViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return QuestionGameViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}