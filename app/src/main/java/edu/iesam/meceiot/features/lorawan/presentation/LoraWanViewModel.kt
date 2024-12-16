package edu.iesam.meceiot.features.lorawan.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.meceiot.core.domain.ErrorApp
import edu.iesam.meceiot.features.lorawan.domain.GetInfoLoraWanUseCase
import edu.iesam.meceiot.features.lorawan.domain.LoraWanInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class LoraWanViewModel(private val getInfoLoraWanUseCase: GetInfoLoraWanUseCase) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState


    fun viewCreated() {
        _uiState.value = UiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000)
            val infoLoraWan = getInfoLoraWanUseCase()
            _uiState.postValue(
                UiState(
                    isLoading = false,
                    infoLoraWan = infoLoraWan.getOrNull(),
                    errorApp = infoLoraWan.exceptionOrNull() as? ErrorApp
                )
            )
        }
    }


    data class UiState(
        val isLoading: Boolean = false,
        val errorApp: ErrorApp? = null,
        val infoLoraWan: List<LoraWanInfo>? = emptyList()
    )
}