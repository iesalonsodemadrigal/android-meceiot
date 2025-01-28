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
import edu.iesam.meceiot.features.sensorpanels.domain.PanelUiModel
import edu.iesam.meceiot.features.sensorpanels.presentation.adapter.SensorPanelsAdapter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SensorPanelsFragment : Fragment() {

    private var _binding: FragmentSensorPanelsBinding? = null
    private val binding get() = _binding!!

    private lateinit var sensorPanelsAdapter: SensorPanelsAdapter
    val viewModel: SensorPanelsViewModel by viewModel()

    private lateinit var skeleton: Skeleton

    private val errorFactory: ErrorAppFactory by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSensorPanelsBinding.inflate(inflater, container, false)
        setupSkeleton()
        setupView()
        return binding.root
    }

    private fun setupSkeleton() {
        val skeletonView = LayoutInflater.from(requireContext())
            .inflate(R.layout.view_skeleton_panels, binding.root, false)
        skeleton = skeletonView.createSkeleton()
    }

    private fun setupView() {
        sensorPanelsAdapter = SensorPanelsAdapter { sensorId ->
            navigateToDetail(sensorId.toInt())
        }
        binding.apply {
            viewToolbar.mainToolbar.title = getString(R.string.label_menu_panels)
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
                                PanelUiModel.Type.PanelUI.value -> 2
                                PanelUiModel.Type.SensorUi.value -> 1
                                else -> 1
                            }
                        }
                    }
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
        setupObservers()
        viewModel.fetchSensorPanels()
    }

    private fun setupObservers() {
        val sensorPanelsObserver = Observer<SensorPanelsViewModel.UiState> { uiState ->
            uiState.sensorPanels?.let { sensorPanels ->
                sensorPanelsAdapter.updateItems(generateListItem(sensorPanels))
                binding.listSensorPanels.adapter = sensorPanelsAdapter
            }
            uiState.errorApp?.let { errorApp ->
                val errorAppUi = errorFactory.build(errorApp, {
                    viewModel.fetchSensorPanels()
                })
                binding.errorApp.render(errorAppUi)
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

    private fun generateListItem(panelList: List<Panel>): List<PanelUiModel> {
        return panelList.map { panel ->
            listOf(panel) + panel.sensors
        }.flatten()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}