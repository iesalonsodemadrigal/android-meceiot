package edu.iesam.meceiot.features.pantallasensor.presentation

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import edu.iesam.meceiot.R
import edu.iesam.meceiot.databinding.BottomSheetDateRangePickerBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DateRangePickerBottomSheet : BottomSheetDialogFragment() {

    private var _binding: BottomSheetDateRangePickerBinding? = null
    private val binding get() = _binding!!

    private val startCalendar = Calendar.getInstance()
    private val endCalendar = Calendar.getInstance()
    private val dateTimeFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetDateRangePickerBinding.inflate(inflater, container, false)
        setupInitialDateTime()
        setupClickListeners()
        return binding.root
    }

    private fun setupInitialDateTime() {
        // Set initial end time to now
        endCalendar.timeInMillis = System.currentTimeMillis()
        // Set initial start time to 6 hours before now (matching default)
        startCalendar.timeInMillis =
            endCalendar.timeInMillis - GraphSensorFragment.DEFAULT_TIME_RANGE
        // Update text views with initial defaults
        updateStartDateTimeText()
        updateEndDateTimeText()
    }

    private fun setupClickListeners() {
        binding.apply {
            // Configurar selectores de fecha y hora
            buttonSelectStartDate.setOnClickListener { showDatePicker(isStart = true) }
            buttonSelectStartTime.setOnClickListener { showTimePicker(isStart = true) }
            buttonSelectEndDate.setOnClickListener { showDatePicker(isStart = false) }
            buttonSelectEndTime.setOnClickListener { showTimePicker(isStart = false) }

            // Botones de acción
            buttonCancel.setOnClickListener { dismiss() }
            buttonApply.setOnClickListener { applyDateRange() }
        }
    }

    private fun showDatePicker(isStart: Boolean) {
        val calendar = if (isStart) startCalendar else endCalendar
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            // Only update date parts
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            // No longer need to set flags
            if (isStart) {
                // startDateSelected = true
                updateStartDateTimeText()
            } else {
                // endDateSelected = true
                updateEndDateTimeText()
            }
        }

        DatePickerDialog(
            requireContext(),
            dateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun showTimePicker(isStart: Boolean) {
        val calendar = if (isStart) startCalendar else endCalendar
        val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            // Only update time parts
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)
            calendar.set(Calendar.SECOND, 0) // Reset seconds
            calendar.set(Calendar.MILLISECOND, 0) // Reset milliseconds
            // No longer need to set flags
            if (isStart) {
                // startTimeSelected = true
                updateStartDateTimeText()
            } else {
                // endTimeSelected = true
                updateEndDateTimeText()
            }
        }

        TimePickerDialog(
            requireContext(),
            timeSetListener,
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true // 24 hour view
        ).show()
    }

    private fun updateStartDateTimeText() {
        binding.textSelectedStartDatetime.text =
            getString(R.string.selected_datetime_format, dateTimeFormat.format(startCalendar.time))
    }

    private fun updateEndDateTimeText() {
        binding.textSelectedEndDatetime.text =
            getString(R.string.selected_datetime_format, dateTimeFormat.format(endCalendar.time))
    }

    private fun applyDateRange() {
        // Keep only the validation: End time must be after start time
        if (endCalendar.timeInMillis <= startCalendar.timeInMillis) {
            Toast.makeText(requireContext(), R.string.error_end_before_start, Toast.LENGTH_SHORT)
                .show()
            return
        }

        // Send result back to GraphSensorFragment
        setFragmentResult(
            GraphSensorFragment.REQUEST_KEY_DATE_RANGE,
            bundleOf(
                GraphSensorFragment.KEY_FROM_TIMESTAMP to startCalendar.timeInMillis,
                GraphSensorFragment.KEY_TO_TIMESTAMP to endCalendar.timeInMillis
            )
        )
        dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 