package edu.iesam.meceiot.features.pantallasensor.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
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
import edu.iesam.meceiot.R
import edu.iesam.meceiot.core.domain.ErrorApp
import edu.iesam.meceiot.core.presentation.hide
import edu.iesam.meceiot.core.presentation.views.ErrorAppFactory
import edu.iesam.meceiot.databinding.FragmentSensorBinding
import edu.iesam.meceiot.features.pantallasensor.domain.GraphSensor
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar
import java.util.Date

class GraphSensorFragment : Fragment() {
    private val graphSensorViewModel: GraphSensorViewModel by viewModel()
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
        return binding.root
    }

    private fun setupView() {
        setupToolbar()

        cartesianChartView = binding.chart
    }

    private fun setupToolbar() {
        val toolbar: MaterialToolbar = binding.toolbar.viewToolbarDetail
        toolbar.inflateMenu(R.menu.options_graph_menu)
        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.fragment_options_graph -> {
                    //findNavController().navigate(R.id.action_sensorFragment_to_optionsGraphFragment)
                    true
                }

                else -> false
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        skeleton = binding.sensorSkeleton
        setupObserver()
        getArgs()?.let {
            graphSensorViewModel.fetchSensorData(it)
        }
    }

    private fun setupObserver() {
        val sensorObserver = Observer<GraphSensorViewModel.UiState> { uiState ->
            checkLoading(uiState.loading)
            bindError(uiState.errorApp)
            bindData(uiState.sensors)
        }
        graphSensorViewModel.uiState.observe(viewLifecycleOwner, sensorObserver)
    }

    private fun checkLoading(isLoading: Boolean) {
        if (isLoading) {
            skeleton.showSkeleton()
        } else {
            skeleton.showOriginal()
        }
    }

    private fun bindError(errorApp: ErrorApp?) {
        errorApp?.let {
            val errorAppUI = ErrorAppFactory(requireContext()).build(it) {
                graphSensorViewModel.fetchSensorData(getArgs())
            }
            binding.errorAppView.render(errorAppUI)
        } ?: run {
            binding.errorAppView.hide()
        }
    }

    private fun bindData(graphSensor: GraphSensor?) {
        graphSensor?.let {
            binding.apply {
                initializeChart(it)
                sensorName.text = it.name
                toolbar.viewToolbarDetail.title = it.panelName
                maxValue.text = it.maxValue
                minValue.text = it.minValue
                avgValue.text = it.avgValue
                modeValue.text = it.modeValue
            }
        }
    }

    private fun getArgs(): Int {
        //Implement the logic to get the sensor id from the arguments
        return 1
    }

    private fun initializeChart(graphSensor: GraphSensor) {
        val chart = cartesianChartView.chart!!
        lifecycleScope.launch {
            modelProducer.runTransaction {
                lineSeries {
                    series(graphSensor.xValues, graphSensor.yValues)
                }
            }
        }
        val valueFormatterYaxis = CartesianValueFormatter { _, y, _ ->
            y.toLong().formatValue(graphSensor.dataType)
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

