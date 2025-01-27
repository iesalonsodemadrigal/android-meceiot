package edu.iesam.meceiot.features.pantallasensor.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.meceiot.core.domain.ErrorApp
import edu.iesam.meceiot.features.pantallasensor.domain.GetSensorDataUseCase
import edu.iesam.meceiot.features.pantallasensor.domain.Sensor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SensorViewModel(private val getSensorDataUseCase: GetSensorDataUseCase) : ViewModel() {
    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState


    fun viewCreated(id: Int) {
        _uiState.value = UiState(loading = true)
        viewModelScope.launch(Dispatchers.IO) {
            val sensor = getSensorDataUseCase.invoke(id)
            _uiState.postValue(
                UiState(
                    loading = false,
                    sensors = sensor.getOrNull(),
                    errorApp = sensor.exceptionOrNull() as? ErrorApp
                )
            )
        }
    }


    data class UiState(
        val loading: Boolean = true,
        val errorApp: ErrorApp? = null,
        val sensors: Sensor? = null,
    )
}