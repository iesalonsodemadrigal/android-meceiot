package edu.iesam.meceiot.features.iot.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.meceiot.core.domain.ErrorApp
import edu.iesam.meceiot.features.iot.domain.GetIoTListUseCase
import edu.iesam.meceiot.features.iot.domain.IoT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class IoTViewModel(private val getInfoIoTUseCase: GetIoTListUseCase) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun viewCreated() {
        _uiState.value = UiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            val infoIoT = getInfoIoTUseCase()
            _uiState.postValue(
                UiState(
                    isLoading = false,


                )
            )
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val errorApp: ErrorApp? = null,
        val infoIoT: List<IoT>? = emptyList()
    )
}