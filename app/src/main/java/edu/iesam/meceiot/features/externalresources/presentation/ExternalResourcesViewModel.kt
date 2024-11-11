package edu.iesam.meceiot.features.externalresources.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.meceiot.core.domain.ErrorApp
import edu.iesam.meceiot.features.externalresources.domain.ExternalResources
import edu.iesam.meceiot.features.externalresources.domain.GetAllExternalResourcesUseCase
import kotlinx.coroutines.launch

class ExternalResourcesViewModel(private val getAllExternalResourcesUseCase: GetAllExternalResourcesUseCase) :
    ViewModel() {
    private val _uiState = MutableLiveData<UiStage>()
    val uiState: MutableLiveData<UiStage> = _uiState

    fun viewCreated() {
        _uiState.value = UiStage(loading = true)

        viewModelScope.launch() {
            val resources = getAllExternalResourcesUseCase.invoke()
            _uiState.value = UiStage(externalResources = resources)
        }
    }

    data class UiStage(
        val loading: Boolean = false,
        val errorApp: ErrorApp? = null,
        val externalResources: List<ExternalResources>? = null,
    )
}