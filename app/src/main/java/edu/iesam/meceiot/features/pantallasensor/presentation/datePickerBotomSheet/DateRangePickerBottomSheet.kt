package edu.iesam.meceiot.features.pantallasensor.presentation.datePickerBotomSheet

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import edu.iesam.meceiot.R
import edu.iesam.meceiot.databinding.BottomSheetDateRangePickerBinding
import edu.iesam.meceiot.features.pantallasensor.presentation.GraphSensorFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar

class DateRangePickerBottomSheet : BottomSheetDialogFragment() {

    companion object {
        private const val DATE_PICKER_TAG = "DATE_PICKER"
        private const val TIME_PICKER_TAG = "TIME_PICKER"
    }

    private var _binding: BottomSheetDateRangePickerBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DateRangePickerViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(
            STYLE_NORMAL,
            com.google.android.material.R.style.ThemeOverlay_Material3_BottomSheetDialog
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetDateRangePickerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        observeViewModel()
    }

    private fun setupClickListeners() = with(binding) {
        buttonSelectStartDate.setOnClickListener { showDatePicker(isStart = true) }
        buttonSelectStartTime.setOnClickListener { showTimePicker(isStart = true) }
        buttonSelectEndDate.setOnClickListener { showDatePicker(isStart = false) }
        buttonSelectEndTime.setOnClickListener { showTimePicker(isStart = false) }

        buttonCancel.setOnClickListener { dismiss() }
        buttonApply.setOnClickListener { viewModel.applyDateRange() }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    updateDateTimeDisplay(state)
                    handleErrorState(state.error)
                    processResult(state.result)
                }
            }
        }
    }

    private fun updateDateTimeDisplay(state: DateRangePickerViewModel.UiState) = with(binding) {
        textSelectedStartDatetime.text = getString(
            R.string.selected_datetime_format, // Recurso String
            state.startDateTimeString          // Valor del ViewModel (solo fecha/hora)
        )
        textSelectedEndDatetime.text = getString(
            R.string.selected_datetime_format, // Recurso String
            state.endDateTimeString            // Valor del ViewModel (solo fecha/hora)
        )
    }

    // Actualizado para usar DateRangeError
    private fun handleErrorState(error: DateRangeError?) {
        error?.let {
            val errorMessage = when (it) {
                is DateRangeError.SelectionIncomplete -> getString(R.string.error_select_all_datetime)
                is DateRangeError.EndBeforeStart -> getString(R.string.error_end_before_start)
                // Agregar aquí más casos si se añaden más tipos de error
            }
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
            viewModel.errorShown()
        }
    }

    private fun processResult(result: Pair<Long, Long>?) {
        result?.let { (startTime, endTime) ->
            setFragmentResult(
                GraphSensorFragment.Companion.REQUEST_KEY_DATE_RANGE,
                bundleOf(
                    GraphSensorFragment.Companion.KEY_FROM_TIMESTAMP to startTime,
                    GraphSensorFragment.Companion.KEY_TO_TIMESTAMP to endTime
                )
            )
            viewModel.resultSent()
            dismiss()
        }
    }

    private fun showDatePicker(isStart: Boolean) {
        val currentCalendar = if (isStart)
            viewModel.uiState.value.startCalendar
        else
            viewModel.uiState.value.endCalendar

        DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                viewModel.updateDate(isStart, year, month, dayOfMonth)
            },
            currentCalendar.get(Calendar.YEAR),
            currentCalendar.get(Calendar.MONTH),
            currentCalendar.get(Calendar.DAY_OF_MONTH)
        ).apply {
            // Agregamos tag para facilitar pruebas
            //if (isStart) tag = "$DATE_PICKER_TAG-START" else tag = "$DATE_PICKER_TAG-END"
            show()
        }
    }

    private fun showTimePicker(isStart: Boolean) {
        val currentCalendar = if (isStart)
            viewModel.uiState.value.startCalendar
        else
            viewModel.uiState.value.endCalendar

        TimePickerDialog(
            requireContext(),
            { _, hourOfDay, minute ->
                viewModel.updateTime(isStart, hourOfDay, minute)
            },
            currentCalendar.get(Calendar.HOUR_OF_DAY),
            currentCalendar.get(Calendar.MINUTE),
            true // Formato 24 horas
        ).apply {
            // Agregamos tag para facilitar pruebas
            //if (isStart) tag = "$TIME_PICKER_TAG-START" else tag = "$TIME_PICKER_TAG-END"
            show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}