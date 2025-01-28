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
import com.faltenreich.skeletonlayout.Skeleton
import com.google.android.material.appbar.MaterialToolbar
import com.patrykandpatrick.vico.core.cartesian.Zoom
import com.patrykandpatrick.vico.core.cartesian.axis.HorizontalAxis
import com.patrykandpatrick.vico.core.cartesian.axis.VerticalAxis
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.CartesianValueFormatter
import com.patrykandpatrick.vico.core.cartesian.data.lineSeries
import com.patrykandpatrick.vico.views.cartesian.CartesianChartView
import com.patrykandpatrick.vico.views.cartesian.ZoomHandler
import edu.iesam.meceiot.core.presentation.hide
import edu.iesam.meceiot.core.presentation.views.ErrorAppFactory
import edu.iesam.meceiot.databinding.FragmentSensorBinding
import edu.iesam.meceiot.features.pantallasensor.domain.Sensor
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar
import java.util.Date

class SensorFragment : Fragment() {
    private val sensorViewModel: SensorViewModel by viewModel()
    private var _binding: FragmentSensorBinding? = null
    private val binding get() = _binding!!
    private lateinit var cartesianChartView: CartesianChartView
    private val modelProducer = CartesianChartModelProducer()
    private lateinit var skeleton: Skeleton
    private val valueFormatterXaxis = CartesianValueFormatter { _, x, _ ->
        x.toLong().toHourAndMinute()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSensorBinding.inflate(inflater, container, false)
        setupView()
        cartesianChartView.modelProducer = modelProducer
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun setupView() {
        val toolbar: MaterialToolbar = binding.toolbar.viewToolbarDetail
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        val actionBar: ActionBar? = (activity as AppCompatActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeButtonEnabled(true)
        cartesianChartView = binding.chart
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        skeleton = binding.sensorSkeleton
        setupObserver()
        getArgs()?.let {
            sensorViewModel.fetchSensorData(it)
        }
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


    private fun setupObserver() {
        val sensorObserver = Observer<SensorViewModel.UiState> { uiState ->

            uiState.sensors?.let { sensor ->
                bindData(sensor)
            }
            uiState.errorApp?.let {
                val errorAppUI = ErrorAppFactory(requireContext()).build(it) {
                    getArgs()?.let { sensorId -> sensorViewModel.fetchSensorData(sensorId) }
                }
                binding.errorAppView.render(errorAppUI)
            } ?: run {
                binding.errorAppView.hide()
            }
            if (uiState.loading) {
                skeleton.showSkeleton()
            } else {
                skeleton.showOriginal()
            }
        }
        sensorViewModel.uiState.observe(viewLifecycleOwner, sensorObserver)
    }

    private fun getArgs(): Int? {
        //Implement the logic to get the sensor id from the arguments
        return 1
    }

    private fun bindData(sensor: Sensor) {
        binding.apply {
            initializeChart(sensor)
            sensorName.text = sensor.name
            toolbar.viewToolbarDetail.title = sensor.panelName
            maxValue.text = sensor.maxValue
            minValue.text = sensor.minValue
            avgValue.text = sensor.avgValue
            modeValue.text = sensor.modeValue
        }
    }

    private fun initializeChart(sensor: Sensor) {
        val chart = cartesianChartView.chart!!
        GlobalScope.launch {
            modelProducer.runTransaction {
                lineSeries {
                    series(sensor.xValues, sensor.yValues)
                }
            }
        }
        val valueFormatterYaxis = CartesianValueFormatter { _, y, _ ->
            y.toLong().formatValue(sensor.dataType)
        }
        cartesianChartView.chart = chart.copy(
            bottomAxis = (chart.bottomAxis as HorizontalAxis).copy(valueFormatter = valueFormatterXaxis),
            startAxis = (chart.startAxis as VerticalAxis).copy(valueFormatter = valueFormatterYaxis),
        )
        cartesianChartView.zoomHandler = ZoomHandler(
            zoomEnabled = true,
            initialZoom = Zoom.Content,
        )
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun Long.toHourAndMinute(): String {
        val date = Date(this) // Assuming the long value is in milliseconds
        val calendar = Calendar.getInstance().apply { time = date }
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        return String.format("%02d:%02d", hour, minute)
    }

    private fun Long.formatValue(dataType: String): String {
        return String.format("%02d %s", this, dataType)
    }

}

