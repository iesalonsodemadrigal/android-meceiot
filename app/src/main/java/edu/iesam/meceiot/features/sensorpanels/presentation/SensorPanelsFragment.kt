package edu.iesam.meceiot.features.sensorpanels.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import edu.iesam.meceiot.R
import edu.iesam.meceiot.core.presentation.hide
import edu.iesam.meceiot.core.presentation.views.ErrorAppFactory
import edu.iesam.meceiot.databinding.FragmentSensorPanelsBinding
import edu.iesam.meceiot.features.sensorpanels.presentation.adapter.SensorPanelsAdapter
import edu.iesam.meceiot.features.sensorpanels.presentation.ui.ViewTypeUi
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SensorPanelsFragment : Fragment() {

    private var _binding: FragmentSensorPanelsBinding? = null
    private val binding get() = _binding!!

    private lateinit var sensorPanelsAdapter: SensorPanelsAdapter
    val viewModel: SensorPanelsViewModel by viewModel()

    private val errorFactory: ErrorAppFactory by inject()

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
        sensorPanelsAdapter = SensorPanelsAdapter { sensorName, panelName, query, sensorId ->
            navigateToDetail(
                sensorName = sensorName,
                panelName = panelName,
                query = query,
                sensorId = sensorId
            )
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
                                ViewTypeUi.Type.PanelUI.value -> 2
                                ViewTypeUi.Type.SensorUi.value -> 1
                                else -> 1
                            }
                        }
                    }
                }
                setupRecyclerView()
                adapter = sensorPanelsAdapter
            }
            skeleton = skeletonPanels.skeletonLayout
        }
    }

    private fun setupRecyclerView() {
        val verticalDecorator =
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        val horizontalDecorator =
            DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL)

        val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.dividers)!!
        verticalDecorator.setDrawable(drawable)
        horizontalDecorator.setDrawable(drawable)

        binding.listSensorPanels.addItemDecoration(verticalDecorator);
        binding.listSensorPanels.addItemDecoration(horizontalDecorator);
    }

    private fun navigateToDetail(
        sensorName: String,
        panelName: String,
        query: String,
        sensorId: Int
    ) {
        findNavController().navigate(
            SensorPanelsFragmentDirections
                .actionSensorPanelsFragmentToSensorFragment(
                    sensorName = sensorName,
                    panelName = panelName,
                    query = query,
                    sensorId = sensorId.toString()
                )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        viewModel.fetchSensorPanels()
    }

    private fun setupObservers() {
        val sensorPanelsObserver = Observer<SensorPanelsViewModel.UiState> { uiState ->
            uiState.sensorPanels?.let { listItems ->
                sensorPanelsAdapter.updateItems(listItems)
                binding.listSensorPanels.adapter = sensorPanelsAdapter
            }
            uiState.errorApp?.let { errorApp ->
                val errorAppUi = errorFactory.build(errorApp) {
                    viewModel.fetchSensorPanels()
                }
                binding.errorApp.render(errorAppUi)
            } ?: run {
                binding.errorApp.hide()
            }

            if (uiState.isLoading) {
                binding.skeletonPanels.skeletonLayout.visibility = View.VISIBLE
                skeleton.showSkeleton()
            } else {
                binding.skeletonPanels.skeletonLayout.visibility = View.GONE
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, sensorPanelsObserver)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}