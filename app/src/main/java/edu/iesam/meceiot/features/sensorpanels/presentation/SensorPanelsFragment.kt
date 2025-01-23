package edu.iesam.meceiot.features.sensorpanels.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.createSkeleton
import edu.iesam.meceiot.R
import edu.iesam.meceiot.core.presentation.hide
import edu.iesam.meceiot.core.presentation.views.ErrorAppFactory
import edu.iesam.meceiot.databinding.FragmentSensorPanelsBinding
import edu.iesam.meceiot.features.sensorpanels.domain.Panel
import edu.iesam.meceiot.features.sensorpanels.presentation.adapter.ListItem
import edu.iesam.meceiot.features.sensorpanels.presentation.adapter.SensorPanelsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class SensorPanelsFragment : Fragment() {

    private var _binding: FragmentSensorPanelsBinding? = null
    private val binding get() = _binding!!

    lateinit var sensorPanelsAdapter: SensorPanelsAdapter
    val viewModel: SensorPanelsViewModel by viewModel()
    private var errorFactory: ErrorAppFactory? = null

    private lateinit var skeleton: Skeleton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSensorPanelsBinding.inflate(inflater, container, false)

        setupView()

        return binding.root
    }

    private fun setupView() {
        sensorPanelsAdapter = SensorPanelsAdapter()
        binding.apply {
            listSensorPanels.apply {
                layoutManager = GridLayoutManager(
                    context,
                    2,
                    GridLayoutManager.VERTICAL,
                    false
                ).apply {
                    spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                        override fun getSpanSize(position: Int): Int {
                            return when (sensorPanelsAdapter.getItemViewType(position)) {
                                ListItem.Type.PANEL.value -> 2
                                ListItem.Type.SENSOR.value -> 1
                                else -> 1
                            }
                        }
                    }
                }
                sensorPanelsAdapter.setOnClickListener { sensorId ->
                    navigateToDetail(sensorId.toInt())
                }
                adapter = sensorPanelsAdapter
            }
        }
    }

    private fun navigateToDetail(sensorId: Int) {
        /*findNavController().navigate(
            SensorPanelsFragmentDirections
                .actionSensorPanelsFragmentToSensorPanelDetailFragment(sensorId
            )
        )*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSkeleton()
        setupObservers()
        viewModel.fetchSensorPanels()
    }

    private fun setupSkeleton() {
        val skeletonView = LayoutInflater.from(requireContext())
            .inflate(R.layout.view_skeleton_panels, binding.root, false)
        skeleton = skeletonView.createSkeleton()
    }

    private fun setupObservers() {
        val sensorPanelsObserver = Observer<SensorPanelsViewModel.UiState> { uiState ->
            uiState.sensorPanels?.let { sensorPanels ->
                sensorPanelsAdapter.updateItems(generateListItem(sensorPanels))
                binding.listSensorPanels.adapter = sensorPanelsAdapter
            }
            uiState.errorApp?.let { errorApp ->
                val errorAppUi = errorFactory?.build(errorApp)
                binding.errorApp.render(errorAppUi!!)
            } ?: run {
                binding.errorApp.hide()
            }

            if (uiState.isLoading) {
                skeleton.showSkeleton()
            } else {
                skeleton.showOriginal()
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, sensorPanelsObserver)
    }

    private fun generateListItem(panelList: List<Panel>): List<ListItem> {
        val list = mutableListOf<ListItem>()
        panelList.forEach { panel ->
            list.add(panel)
            panel.sensors.forEach { sensor ->
                list.add(sensor)
            }
        }
        return list
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}