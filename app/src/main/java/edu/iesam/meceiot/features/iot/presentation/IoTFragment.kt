package edu.iesam.meceiot.features.iot.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import edu.iesam.meceiot.databinding.FragmentIotBinding
import edu.iesam.meceiot.features.iot.domain.IoT
import edu.iesam.meceiot.features.iot.presentation.adapter.IoTAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class IoTFragment : Fragment() {

    private val iotViewModel: IoTViewModel by viewModel()

    private var _binding: FragmentIotBinding? = null
    private val binding get() = _binding!!

    private val iotAdapter = IoTAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIotBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        iotViewModel.viewCreated()
    }

    private fun setupObserver() {
        val iotObserver = Observer<IoTViewModel.UiState> { uiState ->
            uiState.infoIoT?.let {
                bindData(it)
            }
            uiState.errorApp?.let {
                //print error
            } ?: run {
                // hide error
            }
            if (uiState.isLoading) {
                //show loading
            } else {
                //hide loading
            }
        }
        iotViewModel.uiState.observe(viewLifecycleOwner, iotObserver)
    }

    private fun setupView() {
        binding.apply {
            recyclerViewIoT.layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.VERTICAL, false
            )
            recyclerViewIoT.adapter = iotAdapter
        }
    }

    private fun bindData(iotInfo: List<IoT>) {
        iotAdapter.submitList(iotInfo.sortedBy { it.id.toInt() })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}