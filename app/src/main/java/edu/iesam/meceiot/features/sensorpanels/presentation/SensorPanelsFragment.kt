package edu.iesam.meceiot.features.sensorpanels.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import edu.iesam.meceiot.core.presentation.hide
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
    //private var errorFactory: ErrorAppFactory? = null

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
        sensorPanelsAdapter = SensorPanelsAdapter(emptyList())
        binding.listSensorPanels.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = sensorPanelsAdapter
        }
        sensorPanelsAdapter.setOnClickListener { sensorId ->
            navigateToDetail(sensorId.toInt())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchSensorPanels()
        setupObservers()
    }

    private fun setupObservers() {
        val sensorPanelsObserver = Observer<SensorPanelsViewModel.UiState> { uiState ->
            uiState.sensorPanels?.let { sensorPanels ->
                binding.listSensorPanels.adapter =
                    SensorPanelsAdapter(generateListItem(sensorPanels))
            }
            uiState.errorApp?.let { errorApp ->
                /*val errorAppUi = errorFactory?.build(errorApp)
                binding.errorApp.render(errorAppUi!!)*/
            } ?: run {
                binding.errorApp.hide()
            }

            if (uiState.isLoading) {
                //Pintar Skeleton
                Log.d("@dev", "Cargando...")
            } else {
                //Ocultar Skeleton
                Log.d("@dev", "Cargando...")
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

    private fun navigateToDetail(sensorId: Int) {
        /*findNavController().navigate(
            SensorPanelsFragmentDirections
                .actionSensorPanelsFragmentToSensorPanelDetailFragment(sensorId
            )
        )*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}