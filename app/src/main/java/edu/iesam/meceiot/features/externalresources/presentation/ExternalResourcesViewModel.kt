package edu.iesam.meceiot.features.externalresources.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.meceiot.core.domain.ErrorApp
import edu.iesam.meceiot.features.externalresources.domain.ExternalResources
import edu.iesam.meceiot.features.externalresources.domain.GetAllExternalResourcesUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class ExternalResourcesViewModel(private val getAllExternalResourcesUseCase: GetAllExternalResourcesUseCase) :
    ViewModel() {
    private val _uiState = MutableLiveData<UiState>()
    val uiState: MutableLiveData<UiState> = _uiState

    fun viewCreated() {
        _uiState.value = UiState(loading = true)

        viewModelScope.launch {
            val resources = getAllExternalResourcesUseCase.invoke()
            resources.fold({
                _uiState.value = UiState(externalResources = it)
            }, {
                _uiState.value = UiState(errorApp = it as ErrorApp)
            })
            _uiState.value = _uiState.value?.copy(loading = false)
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val errorApp: ErrorApp? = null,
        val externalResources: List<ExternalResources>? = emptyList()
    )
}