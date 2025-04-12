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
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import edu.iesam.meceiot.databinding.BottomSheetDateRangePickerBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar

class DateRangePickerBottomSheet : BottomSheetDialogFragment() {

    private var _binding: BottomSheetDateRangePickerBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DateRangePickerViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetDateRangePickerBinding.inflate(inflater, container, false)
        setupClickListeners()
        observeViewModel() 
        return binding.root
    }

    private fun setupClickListeners() {
        binding.apply {
            buttonSelectStartDate.setOnClickListener { showDatePicker(isStart = true) }
            buttonSelectStartTime.setOnClickListener { showTimePicker(isStart = true) }
            buttonSelectEndDate.setOnClickListener { showDatePicker(isStart = false) }
            buttonSelectEndTime.setOnClickListener { showTimePicker(isStart = false) }

            buttonCancel.setOnClickListener { dismiss() }
            buttonApply.setOnClickListener { viewModel.applyDateRange() }
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    binding.textSelectedStartDatetime.text = state.startDateTimeString
                    binding.textSelectedEndDatetime.text = state.endDateTimeString

                    state.error?.let { errorMessage ->
                        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
                        viewModel.errorShown()
                    }

                    state.result?.let { (startTime, endTime) ->
                        setFragmentResult(
                            GraphSensorFragment.REQUEST_KEY_DATE_RANGE,
                            bundleOf(
                                GraphSensorFragment.KEY_FROM_TIMESTAMP to startTime,
                                GraphSensorFragment.KEY_TO_TIMESTAMP to endTime
                            )
                        )
                        viewModel.resultSent()
                        dismiss()
                    }
                }
            }
        }
    }

    private fun showDatePicker(isStart: Boolean) {
        val currentCalendar =
            if (isStart) viewModel.uiState.value.startCalendar else viewModel.uiState.value.endCalendar

        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            if (isStart) {
                viewModel.updateStartDate(year, month, dayOfMonth)
            } else {
                viewModel.updateEndDate(year, month, dayOfMonth)
            }
        }

        DatePickerDialog(
            requireContext(),
            dateSetListener,
            currentCalendar.get(Calendar.YEAR),
            currentCalendar.get(Calendar.MONTH),
            currentCalendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun showTimePicker(isStart: Boolean) {
        val currentCalendar =
            if (isStart) viewModel.uiState.value.startCalendar else viewModel.uiState.value.endCalendar

        val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            if (isStart) {
                viewModel.updateStartTime(hourOfDay, minute)
            } else {
                viewModel.updateEndTime(hourOfDay, minute)
            }
        }

        TimePickerDialog(
            requireContext(),
            timeSetListener,
            currentCalendar.get(Calendar.HOUR_OF_DAY),
            currentCalendar.get(Calendar.MINUTE),
            true
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}