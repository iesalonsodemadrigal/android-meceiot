package edu.iesam.meceiot.features.pantallasensor.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.meceiot.core.domain.ErrorApp
import edu.iesam.meceiot.features.pantallasensor.domain.GetSensorDataUseCase
import edu.iesam.meceiot.features.pantallasensor.domain.Sensor
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SensorViewModel(private val getSensorDataUseCase: GetSensorDataUseCase) : ViewModel() {
    private val _uiState = MutableLiveData<UiState>()
    val uiState: MutableLiveData<UiState> = _uiState

    fun viewCreated() {
        _uiState.value = UiState(loading = true)

        viewModelScope.launch {
            try {
                val sensors = getSensorDataUseCase.invoke(1)
                _uiState.value = UiState(sensors = sensors)
            } catch (e: Exception) {
                _uiState.value = UiState(errorApp = e as ErrorApp)
            } finally {
                _uiState.value = _uiState.value?.copy(loading = false)
            }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val errorApp: ErrorApp? = null,
        val sensors: Sensor? = null // Change to List<Sensor> if needed
    )
}