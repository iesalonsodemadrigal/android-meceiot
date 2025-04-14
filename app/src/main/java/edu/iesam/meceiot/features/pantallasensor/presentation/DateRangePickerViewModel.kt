package edu.iesam.meceiot.features.pantallasensor.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.android.annotation.KoinViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

// Definir los posibles errores de validación
sealed class DateRangeError {
    object SelectionIncomplete : DateRangeError()
    object EndBeforeStart : DateRangeError()
    // Se pueden añadir más errores específicos aquí
}

@KoinViewModel
class DateRangePickerViewModel(
    private val dateTimeFormatter: SimpleDateFormat = SimpleDateFormat(
        "yyyy-MM-dd HH:mm",
        Locale.getDefault()
    )
) : ViewModel() {

    // Define un estado para contener las fechas y sus representaciones en texto
    data class UiState(
        val startCalendar: Calendar = Calendar.getInstance(),
        val endCalendar: Calendar = Calendar.getInstance(),
        val startDateTimeString: String = "",
        val endDateTimeString: String = "",
        val error: DateRangeError? = null, // Ahora usamos el tipo sellado para errores
        val result: Pair<Long, Long>? = null // Para enviar el resultado final
    )

    // MutableStateFlow para el estado interno
    private val _uiState = MutableStateFlow(UiState())

    // StateFlow público e inmutable para que la UI observe
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    companion object {
        private const val DEFAULT_TIME_RANGE_HOURS = 6
        private const val DEFAULT_TIME_RANGE_MS = DEFAULT_TIME_RANGE_HOURS * 60 * 60 * 1000L
    }

    init {
        // Establecer el estado inicial al crear el ViewModel
        initializeDates()
    }

    private fun initializeDates() {
        val initialEndCalendar = Calendar.getInstance()
        val initialStartCalendar = Calendar.getInstance()

        // Set initial end time to now
        initialEndCalendar.timeInMillis = System.currentTimeMillis()
        // Set initial start time to 6 hours before now
        initialStartCalendar.timeInMillis = initialEndCalendar.timeInMillis - DEFAULT_TIME_RANGE_MS

        _uiState.update { currentState ->
            currentState.copy(
                startCalendar = initialStartCalendar,
                endCalendar = initialEndCalendar,
                startDateTimeString = formatDateTime(initialStartCalendar),
                endDateTimeString = formatDateTime(initialEndCalendar),
                error = null, // Limpiar errores previos
                result = null // Limpiar resultado previo
            )
        }
    }

    // Función refactorizada para actualizar los calendarios que reduce duplicación
    private fun updateCalendar(isStart: Boolean, updater: (Calendar) -> Calendar) {
        _uiState.update { currentState ->
            val calendar = if (isStart) currentState.startCalendar else currentState.endCalendar
            val updatedCalendar = updater(calendar.clone() as Calendar)

            if (isStart) {
                currentState.copy(
                    startCalendar = updatedCalendar,
                    startDateTimeString = formatDateTime(updatedCalendar),
                    error = null,
                    result = null
                )
            } else {
                currentState.copy(
                    endCalendar = updatedCalendar,
                    endDateTimeString = formatDateTime(updatedCalendar),
                    error = null,
                    result = null
                )
            }
        }
    }

    // Función para actualizar la fecha (inicio o fin)
    fun updateDate(isStart: Boolean, year: Int, month: Int, dayOfMonth: Int) {
        updateCalendar(isStart) { calendar ->
            calendar.apply {
                set(Calendar.YEAR, year)
                set(Calendar.MONTH, month)
                set(Calendar.DAY_OF_MONTH, dayOfMonth)
            }
        }
    }

    // Función para actualizar la hora (inicio o fin)
    fun updateTime(isStart: Boolean, hourOfDay: Int, minute: Int) {
        updateCalendar(isStart) { calendar ->
            calendar.apply {
                set(Calendar.HOUR_OF_DAY, hourOfDay)
                set(Calendar.MINUTE, minute)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }
        }
    }

    // Mantener compatibilidad con el código existente
    /*fun updateStartDate(year: Int, month: Int, dayOfMonth: Int) {
        updateDate(true, year, month, dayOfMonth)
    }

    fun updateStartTime(hourOfDay: Int, minute: Int) {
        updateTime(true, hourOfDay, minute)
    }

    fun updateEndDate(year: Int, month: Int, dayOfMonth: Int) {
        updateDate(false, year, month, dayOfMonth)
    }

    fun updateEndTime(hourOfDay: Int, minute: Int) {
        updateTime(false, hourOfDay, minute)
    }*/

    // Función para aplicar la selección con validaciones mejoradas usando sealed class
    fun applyDateRange() {
        val currentState = _uiState.value // Obtener el estado actual

        when {
            currentState.startDateTimeString.isEmpty() || currentState.endDateTimeString.isEmpty() -> {
                _uiState.update {
                    it.copy(
                        error = DateRangeError.SelectionIncomplete,
                    result = null
                    )
                }
            }

            currentState.endCalendar.timeInMillis <= currentState.startCalendar.timeInMillis -> {
                _uiState.update {
                    it.copy(
                        error = DateRangeError.EndBeforeStart,
                        result = null
                    )
                }
            }

            else -> {
                _uiState.update {
                    it.copy(
                        error = null,
                        result = Pair(
                            currentState.startCalendar.timeInMillis,
                            currentState.endCalendar.timeInMillis
                        )
                    )
                }
            }
        }
    }

    // Función para indicar que el error ha sido mostrado/manejado por la UI
    fun errorShown() {
        _uiState.update { it.copy(error = null) }
    }

    // Función para indicar que el resultado ha sido enviado/manejado por la UI
    fun resultSent() {
        _uiState.update { it.copy(result = null) }
    }

    // Helper para formatear
    private fun formatDateTime(calendar: Calendar): String {
        return dateTimeFormatter.format(calendar.time)
    }
}