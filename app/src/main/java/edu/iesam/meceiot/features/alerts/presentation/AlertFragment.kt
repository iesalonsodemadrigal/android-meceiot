package edu.iesam.meceiot.features.alerts.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import edu.iesam.meceiot.core.presentation.hide
import edu.iesam.meceiot.core.presentation.views.ErrorAppFactory
import edu.iesam.meceiot.databinding.FragmentAlertBinding
import edu.iesam.meceiot.features.alerts.domain.Zone
import edu.iesam.meceiot.features.alerts.presentation.adapter.AlertAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlertFragment : Fragment() {

    private var _binding: FragmentAlertBinding? = null
    private val binding get() = _binding!!
    private val alertViewModel: AlertViewModel by viewModel()
    private val alertAdapter = AlertAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlertBinding.inflate(inflater, container, false)
        setupView()
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver() //before create the view, we must to observe it
        alertViewModel.viewCreated()
    }

    private fun setupView() {
        binding.apply {
            alertsRecyclerview.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            alertsRecyclerview.adapter = alertAdapter
        }
    }

    private fun setupObserver() {
        val alertObserver = Observer<AlertViewModel.UiState> { uiState ->
            uiState.alert?.let {
                binData(it)
            }
            uiState.errorApp?.let {
                val error = ErrorAppFactory(requireContext())
                val errorAppUi = error.build(it)
                binding.errorAppView.render(errorAppUi)
            } ?: run {
                binding.errorAppView.hide()
            }
            if (uiState.isLoading) {

            } else {

            }
        }
        alertViewModel.uiState.observe(viewLifecycleOwner, alertObserver)

    }

    private fun binData(alert: List<Zone>) {
        alertAdapter.submitList(alert)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}