package edu.iesam.meceiot.features.developer.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.meceiot.core.domain.ErrorApp
import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo
import edu.iesam.meceiot.features.developer.domain.usecase.GetDevelopersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DeveloperAboutViewModel(private val getDevelopersUseCase: GetDevelopersUseCase) :
    ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun viewDevelopers() {
        _uiState.value = UiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            val infoDeveloper = getDevelopersUseCase()
            _uiState.postValue(UiState(infoDeveloper = infoDeveloper))
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val errorMessage: ErrorApp? = null,
        val infoDeveloper: List<DeveloperInfo>? = null
    )


}