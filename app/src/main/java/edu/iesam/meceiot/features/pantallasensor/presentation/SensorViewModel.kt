package edu.iesam.meceiot.features.pantallasensor.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.lineSeries
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

    fun viewCreated() {
        _uiState.value = UiState(loading = true)
        viewModelScope.launch(Dispatchers.IO) {
            val sensor = getSensorDataUseCase.invoke(1)
            _uiState.postValue(
                UiState(
                    loading = false,
                    sensors = sensor,
                    chartData = generateChartData(sensor)
                )
            )
        }

    }

    private suspend fun generateChartData(sensor: Sensor): CartesianChartModelProducer {
        val producer = CartesianChartModelProducer()
        producer.runTransaction {
            lineSeries {
                series(sensor.valoresX, sensor.valoresY)
            }
        }

        return producer
    }

    data class UiState(
        val loading: Boolean = false,
        val errorApp: ErrorApp? = null,
        val sensors: Sensor? = null,
        val chartData: CartesianChartModelProducer? = null
    )
}