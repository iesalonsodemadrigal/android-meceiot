package edu.iesam.meceiot.features.pantallasensor.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.google.android.material.appbar.MaterialToolbar
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.views.cartesian.CartesianChartView
import edu.iesam.meceiot.databinding.FragmentSensorBinding
import edu.iesam.meceiot.features.pantallasensor.domain.Sensor
import org.koin.androidx.viewmodel.ext.android.viewModel

class SensorFragment : Fragment() {
    private val sensorViewModel: SensorViewModel by viewModel()
    private var _binding: FragmentSensorBinding? = null
    private val binding get() = _binding!!
    private lateinit var cartesianChartView: CartesianChartView
    private val modelProducer = CartesianChartModelProducer()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSensorBinding.inflate(inflater, container, false)
        setupView()
        cartesianChartView.modelProducer = modelProducer
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        sensorViewModel.viewCreated()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                findNavController().navigateUp()
                true
            }

            else -> {
                val navController = findNavController()
                item.onNavDestinationSelected(navController)
            }
        }
    }

    private fun setupView() {
        val toolbar: MaterialToolbar = binding.toolbar.viewToolbarDetail
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        val actionBar: ActionBar? = (activity as AppCompatActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeButtonEnabled(true)
        cartesianChartView = binding.chart
    }

    private fun setupObserver() {
        val sensorObserver = Observer<SensorViewModel.UiState> { uiState ->
            uiState.sensors?.let { sensor ->
                bindData(sensor)
                sensorViewModel.updateChartData(sensor, modelProducer)
            }
            uiState.chartData?.let { chartData ->
                cartesianChartView.modelProducer = chartData
            }
        }
        sensorViewModel.uiState.observe(viewLifecycleOwner, sensorObserver)
    }

    private fun bindData(sensor: Sensor) {
        binding.apply {
            nombresensor.text = sensor.nombre
            toolbar.viewToolbarDetail.title = sensor.nombrePanel
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}