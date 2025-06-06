package edu.iesam.meceiot.features.pantallasensor.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.meceiot.core.domain.ErrorApp
import edu.iesam.meceiot.features.pantallasensor.domain.GetGraphSensorDataUseCase
import edu.iesam.meceiot.features.pantallasensor.domain.GraphSensor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class GraphSensorViewModel(private val getGraphSensorDataUseCase: GetGraphSensorDataUseCase) :
    ViewModel() {
    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun fetchSensorData(query: String, from: Long, to: Long) {
        _uiState.value = UiState(loading = true)
        viewModelScope.launch(Dispatchers.IO) {
            // Usamos el hashCode del query como ID único y consistente
            val sensor = getGraphSensorDataUseCase.invoke(query.hashCode(), query, from, to)
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
        val sensors: GraphSensor? = null,
    )
}