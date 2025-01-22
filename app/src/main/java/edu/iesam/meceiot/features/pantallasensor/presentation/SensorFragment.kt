package edu.iesam.meceiot.features.pantallasensor.presentation

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
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
import com.patrykandpatrick.vico.core.cartesian.axis.HorizontalAxis
import com.patrykandpatrick.vico.core.cartesian.axis.VerticalAxis
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.CartesianValueFormatter
import com.patrykandpatrick.vico.views.cartesian.CartesianChartView
import edu.iesam.meceiot.databinding.FragmentSensorBinding
import edu.iesam.meceiot.features.pantallasensor.domain.Sensor
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar
import java.util.Date

class SensorFragment : Fragment() {
    private val sensorViewModel: SensorViewModel by viewModel()
    private var _binding: FragmentSensorBinding? = null
    private val binding get() = _binding!!
    private lateinit var cartesianChartView: CartesianChartView
    private val modelProducer = CartesianChartModelProducer()
    private val valueFormatterXaxis = CartesianValueFormatter { _, x, _ ->
        x.toLong().toHourAndMinute()
    }
    private val valueFormatterYaxis = CartesianValueFormatter { _, y, _ ->
        y.toLong().toPartPerMillion()
    }


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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(edu.iesam.meceiot.R.menu.options_graph_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.home -> {
                findNavController().navigateUp()
                true
            }
            edu.iesam.meceiot.R.id.fragment_options_graph -> {
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
                val chart = cartesianChartView.chart!!
                cartesianChartView.chart = chart.copy(
                    bottomAxis = (chart.bottomAxis as HorizontalAxis).copy(valueFormatter = valueFormatterXaxis),
                    startAxis = (chart.startAxis as VerticalAxis).copy(valueFormatter = valueFormatterYaxis),
                )
            }
            uiState.chartData?.let { chartData ->
                cartesianChartView.modelProducer = chartData
            }
        }
        sensorViewModel.uiState.observe(viewLifecycleOwner, sensorObserver)
    }

    private fun Long.toHourAndMinute(): String {
        val date = Date(this) // Assuming the long value is in milliseconds
        val calendar = Calendar.getInstance().apply { time = date }
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        return String.format("%02d:%02d", hour, minute)
    }

    private fun Long.toPartPerMillion(): String {
        return String.format("%d ppm", this)
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

