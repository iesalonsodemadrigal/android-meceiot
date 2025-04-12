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

// Define un estado para contener las fechas y sus representaciones en texto
data class DateRangeState(
    val startCalendar: Calendar = Calendar.getInstance(),
    val endCalendar: Calendar = Calendar.getInstance(),
    val startDateTimeString: String = "",
    val endDateTimeString: String = "",
    val error: String? = null, // Para mensajes de error (e.g., validación)
    val result: Pair<Long, Long>? = null // Para enviar el resultado final
)

@KoinViewModel
class DateRangePickerViewModel : ViewModel() {

    private val dateTimeFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())

    // MutableStateFlow para el estado interno
    private val _uiState = MutableStateFlow(DateRangeState())

    // StateFlow público e inmutable para que la UI observe
    val uiState: StateFlow<DateRangeState> = _uiState.asStateFlow()

    init {
        // Establecer el estado inicial al crear el ViewModel
        initializeDates()
    }

    private fun initializeDates() {
        val initialEndCalendar = Calendar.getInstance()
        val initialStartCalendar = Calendar.getInstance()

        // Set initial end time to now
        initialEndCalendar.timeInMillis = System.currentTimeMillis()
        // Set initial start time to 6 hours before now (usamos el valor directamente)
        // Asegúrate que DEFAULT_TIME_RANGE sea accesible o pásalo como parámetro si es necesario
        val defaultTimeRange = 6 * 60 * 60 * 1000L // Ejemplo: 6 horas en milisegundos
        initialStartCalendar.timeInMillis = initialEndCalendar.timeInMillis - defaultTimeRange

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

    // Función para actualizar la fecha de inicio
    fun updateStartDate(year: Int, month: Int, dayOfMonth: Int) {
        _uiState.update { currentState ->
            val updatedCalendar = currentState.startCalendar.clone() as Calendar
            updatedCalendar.set(Calendar.YEAR, year)
            updatedCalendar.set(Calendar.MONTH, month)
            updatedCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            currentState.copy(
                startCalendar = updatedCalendar,
                startDateTimeString = formatDateTime(updatedCalendar),
                error = null, // Limpiar error al cambiar fecha
                result = null
            )
        }
    }

    // Función para actualizar la hora de inicio
    fun updateStartTime(hourOfDay: Int, minute: Int) {
        _uiState.update { currentState ->
            val updatedCalendar = currentState.startCalendar.clone() as Calendar
            updatedCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            updatedCalendar.set(Calendar.MINUTE, minute)
            updatedCalendar.set(Calendar.SECOND, 0)
            updatedCalendar.set(Calendar.MILLISECOND, 0)
            currentState.copy(
                startCalendar = updatedCalendar,
                startDateTimeString = formatDateTime(updatedCalendar),
                error = null,
                result = null
            )
        }
    }

    // Funciones similares para updateEndDate y updateEndTime
    fun updateEndDate(year: Int, month: Int, dayOfMonth: Int) {
        _uiState.update { currentState ->
            val updatedCalendar = currentState.endCalendar.clone() as Calendar
            updatedCalendar.set(Calendar.YEAR, year)
            updatedCalendar.set(Calendar.MONTH, month)
            updatedCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            currentState.copy(
                endCalendar = updatedCalendar,
                endDateTimeString = formatDateTime(updatedCalendar),
                error = null,
                result = null
            )
        }
    }

    fun updateEndTime(hourOfDay: Int, minute: Int) {
        _uiState.update { currentState ->
            val updatedCalendar = currentState.endCalendar.clone() as Calendar
            updatedCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            updatedCalendar.set(Calendar.MINUTE, minute)
            updatedCalendar.set(Calendar.SECOND, 0)
            updatedCalendar.set(Calendar.MILLISECOND, 0)
            currentState.copy(
                endCalendar = updatedCalendar,
                endDateTimeString = formatDateTime(updatedCalendar),
                error = null,
                result = null
            )
        }
    }


    // Función para aplicar la selección
    fun applyDateRange() {
        val currentState = _uiState.value // Obtener el estado actual

        // Validación
        if (currentState.endCalendar.timeInMillis <= currentState.startCalendar.timeInMillis) {
            _uiState.update {
                it.copy(
                    error = "La fecha/hora final debe ser posterior a la inicial.",
                    result = null
                )
            }
            return // No continuar si la validación falla
        }

        // Validación exitosa: preparar el resultado
        _uiState.update {
            it.copy(
                error = null, // Limpiar cualquier error previo
                result = Pair(
                    currentState.startCalendar.timeInMillis,
                    currentState.endCalendar.timeInMillis
                )
            )
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
        // Aquí podrías usar también los recursos de String si lo necesitas
        // pasando el Context al ViewModel (menos ideal) o formateando en la UI.
        // Por simplicidad, lo hacemos aquí directamente.
        return "Seleccionado: ${dateTimeFormat.format(calendar.time)}"
        // O si quieres mantener el formato exacto de R.string.selected_datetime_format:
        // return String.format(Locale.getDefault(), "Seleccionado: %s", dateTimeFormat.format(calendar.time))
    }
}