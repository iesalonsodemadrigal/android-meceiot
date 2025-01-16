package edu.iesam.meceiot.features.sensorpanels.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.meceiot.core.domain.ErrorApp
import edu.iesam.meceiot.features.sensorpanels.domain.GetSensorPanelsUseCase
import edu.iesam.meceiot.features.sensorpanels.domain.Panel
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
        viewModelScope.launch(Dispatchers.IO) {
            //DEBUG
            val sensorPanels = getSensorPanelsUseCase.invoke()
            //print log sensor panels
            Log.d("@dev", sensorPanels.toString())

            _uiState.postValue(
                UiState(
                    sensorPanels = sensorPanels.getOrNull(),
                    isLoading = false,
                    errorApp = sensorPanels.exceptionOrNull() as ErrorApp?
                )
            )
        }
    }

    data class UiState(
        val isLoading: Boolean = true,
        val errorApp: ErrorApp? = null,
        val sensorPanels: List<Panel>? = null
    )
}

