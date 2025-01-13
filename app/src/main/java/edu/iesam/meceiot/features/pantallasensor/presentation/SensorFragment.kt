package edu.iesam.meceiot.features.pantallasensor.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.lineSeries
import com.patrykandpatrick.vico.views.cartesian.CartesianChartView
import edu.iesam.meceiot.databinding.FragmentSensorBinding
import edu.iesam.meceiot.features.pantallasensor.domain.Sensor
import org.koin.androidx.viewmodel.ext.android.viewModel

class SensorFragment : Fragment() {
    private val sensorViewModel: SensorViewModel by viewModel()
    private var _binding: FragmentSensorBinding? = null
    private val binding get() = _binding!!
    private lateinit var chart: CartesianChartView
    private val cartesianChartModelProducer = CartesianChartModelProducer()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSensorBinding.inflate(
            inflater,
            container,
            false
        )
        setupView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        sensorViewModel.viewCreated()
    }

    private fun setupView() {
        chart = binding.chart
    }

    private fun setupObserver() {
        val sensorObserver = Observer<SensorViewModel.UiState> { uiState ->
            uiState.sensors?.let { sensor ->
                bindData(sensor)
            }
        }
        sensorViewModel.uiState.observe(viewLifecycleOwner, sensorObserver)
    }

    private fun bindData(sensor: Sensor) {
        binding.apply {
            nombrepanel.text = sensor.nombrePanel
            nombresensor.text = sensor.nombre


        }
    }

    private suspend fun setChartData(sensor: Sensor) {
        cartesianChartModelProducer.runTransaction {
            lineSeries {
                series(sensor.valoresX, sensor.valoresY)
            }
        }
    }

    private suspend fun setChartLabels(sensor: Sensor) {
        cartesianChartModelProducer.runTransaction {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}