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

    fun fetchAlerts() {
        _uiState.value = UiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            val alertResult = getSensorUseCase()
            alertResult.fold(
                onSuccess = { sensors ->
                    // Agrupar sensores por tipo y concatenar ubicaciones
                    val groupedSensors = groupSensorsByType(sensors)
                    _uiState.postValue(
                        UiState(
                            isLoading = false,
                            alert = groupedSensors,
                            errorApp = null
                        )
                    )
                },
                onFailure = { error ->
                    _uiState.postValue(
                        UiState(
                            isLoading = false,
                            alert = emptyList(),
                            errorApp = error as? ErrorApp
                        )
                    )
                }
            )
        }
    }

    private fun groupSensorsByType(sensors: List<Sensor>): List<Sensor> {
        return sensors
            .groupBy { it.type }
            .map { (type, sensorsOfType) ->
                // Crear un único sensor por cada tipo con ubicaciones concatenadas
                Sensor(
                    id = type.type,
                    name = sensorsOfType.first().name,
                    type = type,
                    value = sensorsOfType.first().value,
                    location = sensorsOfType.joinToString("\n") { "${it.name} - ${it.location}" }
                )
            }
    }


    data class UiState(
        val isLoading: Boolean = false,
        val errorApp: ErrorApp? = null, //ErrorApp.UnknowErrorApp
        val alert: List<Sensor>? = emptyList()
    )
}