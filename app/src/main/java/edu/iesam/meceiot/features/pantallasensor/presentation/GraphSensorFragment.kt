package edu.iesam.meceiot.features.pantallasensor.presentation

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Lifecycle
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
import com.patrykandpatrick.vico.core.cartesian.marker.CartesianMarker
import com.patrykandpatrick.vico.core.cartesian.marker.DefaultCartesianMarker
import com.patrykandpatrick.vico.core.common.Dimensions
import com.patrykandpatrick.vico.core.common.component.LineComponent
import com.patrykandpatrick.vico.core.common.component.ShapeComponent
import com.patrykandpatrick.vico.core.common.component.TextComponent
import com.patrykandpatrick.vico.core.common.shape.CorneredShape
import com.patrykandpatrick.vico.core.common.shape.Shape
import com.patrykandpatrick.vico.views.cartesian.CartesianChartView
import com.patrykandpatrick.vico.views.cartesian.ZoomHandler
import edu.iesam.meceiot.R
import edu.iesam.meceiot.core.domain.ErrorApp
import edu.iesam.meceiot.core.presentation.extensions.toFormatDate
import edu.iesam.meceiot.core.presentation.hide
import edu.iesam.meceiot.core.presentation.views.ErrorAppFactory
import edu.iesam.meceiot.core.presentation.visible
import edu.iesam.meceiot.databinding.FragmentSensorBinding
import edu.iesam.meceiot.features.pantallasensor.domain.GraphSensor
import edu.iesam.meceiot.features.sensorpanels.domain.Sensor
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

    companion object {
        const val REQUEST_KEY_DATE_RANGE = "dateRangePickerResult"
        const val KEY_FROM_TIMESTAMP = "fromTimestamp"
        const val KEY_TO_TIMESTAMP = "toTimestamp"
        const val DEFAULT_TIME_RANGE = 6 * 60 * 60 * 1000 // 6 hours
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
        binding.apply {
            chipFilter.apply {
                setOnClickListener {
                    loadCurrentData()
                    hide()
                }
            }
        }
    }

    private fun setupToolbar() {
        binding.toolbar.viewToolbarDetail.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        binding.toolbar.collapsingToolbar.title = getArgs()?.panelName
        binding.toolbar.viewToolbarDetail.let { setupMenu(it) }
    }

    private fun setupMenu(toolbar: MaterialToolbar) {
        toolbar.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.options_graph_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_select_range -> {
                        findNavController().navigate(R.id.action_graphSensorFragment_toDateRangePickerBottomSheet)
                        true
                    }

                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        skeleton = binding.sensorSkeleton
        setupFragmentResultListener()
        setupObserver()
        loadCurrentData()
    }

    private fun loadCurrentData() {
        getArgs()?.let {
            val currentTime = System.currentTimeMillis()
            val fromTime = currentTime - DEFAULT_TIME_RANGE
            graphSensorViewModel.fetchSensorData(it.query, fromTime, currentTime)
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
                val args = getArgs()
                if (args != null) {
                    graphSensorViewModel.fetchSensorData(
                        args.query,
                        System.currentTimeMillis() - DEFAULT_TIME_RANGE,
                        System.currentTimeMillis()
                    )
                }
            }
            binding.errorAppView.render(errorAppUI)
        } ?: run {
            binding.errorAppView.hide()
        }
    }

    private fun bindData(graphSensor: GraphSensor?) {
        val sensor = getArgs()
        graphSensor?.let {
            binding.apply {
                initializeChart(it)
                sensorName.text = getString(R.string.sensor_name, sensor?.name ?: "")
                toolbar.viewToolbarDetail.title = sensor?.panelName
                maxValue.text = it.maxValue
                minValue.text = it.minValue
                avgValue.text = it.avgValue
                modeValue.text = it.modeValue
            }
        }
    }

    private fun getArgs(): Sensor? {
        return arguments?.let {
            val sensorName = GraphSensorFragmentArgs.fromBundle(it).sensorName
            val panelName = GraphSensorFragmentArgs.fromBundle(it).panelName
            val query = GraphSensorFragmentArgs.fromBundle(it).query
            val sensorId = GraphSensorFragmentArgs.fromBundle(it).sensorId
            Sensor(sensorId.toInt(), sensorName, panelName, query)
        }
    }

    private fun setupFragmentResultListener() {
        setFragmentResultListener(REQUEST_KEY_DATE_RANGE) { _, bundle ->
            val fromTimestamp = bundle.getLong(KEY_FROM_TIMESTAMP)
            val toTimestamp = bundle.getLong(KEY_TO_TIMESTAMP)
            // Check timestamps
            if (fromTimestamp > 0 && toTimestamp > 0) {
                getArgs()?.let {
                    graphSensorViewModel.fetchSensorData(
                        it.query,
                        fromTimestamp,
                        toTimestamp
                    )
                    binding.chipFilter?.apply {
                        setText(
                            getString(
                                R.string.graph_filter_date,
                                fromTimestamp.toFormatDate(),
                                toTimestamp.toFormatDate()
                            )
                        )
                        visible()
                    }
                }
            }
        }
    }

    private fun initializeChart(graphSensor: GraphSensor) {
        val chart = cartesianChartView.chart
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

        val marker: CartesianMarker = DefaultCartesianMarker(
            label = TextComponent(
                padding = Dimensions(8f, 4f),
                background = ShapeComponent(
                    shape = CorneredShape.Pill,
                )
            ),
            labelPosition = DefaultCartesianMarker.LabelPosition.Top,
            indicator = { color ->
                LineComponent(
                    color = color,
                    shape = CorneredShape.Pill,
                    shadow = null,
                    thicknessDp = 10f,
                    margins = Dimensions(10f)
                )
            },
            indicatorSizeDp = 30f,
            guideline = LineComponent(
                color = R.color.md_theme_primary,
                thicknessDp = 1f,
                shape = Shape.Rectangle,
                margins = Dimensions.Empty
            )
        )

        cartesianChartView.chart = chart?.copy(
            bottomAxis = ((chart.bottomAxis) as HorizontalAxis).copy(valueFormatter = valueFormatterXaxis),
            startAxis = (chart.startAxis as VerticalAxis).copy(valueFormatter = valueFormatterYaxis),
            marker = marker,
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

