package edu.iesam.meceiot.features.alerts.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.meceiot.core.domain.ErrorApp
import edu.iesam.meceiot.features.alerts.domain.GetSensorUseCase
import edu.iesam.meceiot.features.alerts.domain.Sensor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class AlertViewModel(private val getSensorUseCase: GetSensorUseCase) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun viewCreated() {
        _uiState.value = UiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            val alert = getSensorUseCase()
            _uiState.postValue(
                UiState(
                    isLoading = false,
                    alert = alert.getOrNull(),
                    errorApp = alert.exceptionOrNull() as? ErrorApp
                )
            )
        }
    }


    data class UiState(
        val isLoading: Boolean = false,
        val errorApp: ErrorApp? = null,
        val alert: List<Sensor>? = emptyList()
    )
}