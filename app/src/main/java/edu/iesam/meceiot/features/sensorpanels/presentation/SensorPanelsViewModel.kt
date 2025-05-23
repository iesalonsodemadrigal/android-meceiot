package edu.iesam.meceiot.features.sensorpanels.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.meceiot.core.domain.ErrorApp
import edu.iesam.meceiot.features.sensorpanels.domain.GetSensorPanelsUseCase
import edu.iesam.meceiot.features.sensorpanels.presentation.ui.ViewTypeUi
import edu.iesam.meceiot.features.sensorpanels.presentation.ui.toUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SensorPanelsViewModel(
    private val getSensorPanelsUseCase: GetSensorPanelsUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun fetchSensorPanels() {
        _uiState.value = UiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            val sensorPanels = getSensorPanelsUseCase.invoke()
            sensorPanels.fold(
                onSuccess = { sensorPanels ->
                    val listItems = sensorPanels.map { panel ->
                        listOf<ViewTypeUi>(
                            panel.toUiModel()
                        ) + panel.sensors.map { it.toUiModel() }
                    }.flatten()
                    _uiState.postValue(
                        UiState(
                            sensorPanels = listItems,
                            isLoading = false
                        )
                    )
                },
                onFailure = { error ->
                    _uiState.postValue(
                        UiState(
                            sensorPanels = null,
                            isLoading = false,
                            errorApp = error as? ErrorApp ?: ErrorApp.UnknowErrorApp
                        )
                    )
                }
            )
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val errorApp: ErrorApp? = null,
        val sensorPanels: List<ViewTypeUi>? = null
    )
}

