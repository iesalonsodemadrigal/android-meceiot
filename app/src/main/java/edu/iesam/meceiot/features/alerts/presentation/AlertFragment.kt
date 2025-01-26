package edu.iesam.meceiot.features.alerts.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import edu.iesam.meceiot.R
import edu.iesam.meceiot.core.presentation.hide
import edu.iesam.meceiot.core.presentation.views.ErrorAppFactory
import edu.iesam.meceiot.databinding.FragmentAlertBinding
import edu.iesam.meceiot.features.alerts.domain.Sensor
import edu.iesam.meceiot.features.alerts.presentation.adapter.AlertAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlertFragment : Fragment() {

    private var _binding: FragmentAlertBinding? = null
    private val binding get() = _binding!!
    private val alertViewModel: AlertViewModel by viewModel()
    private val alertAdapter = AlertAdapter()
    private lateinit var skeleton: Skeleton

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
        setupObserver()
        alertViewModel.viewCreated()
    }

    private fun setupView() {
        binding.apply {
            mainToolbarAlerts.mainToolbar.title = getString(R.string.alerts_title)
            alertsRecyclerview.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            alertsRecyclerview.adapter = alertAdapter
            skeleton = alertsRecyclerview.applySkeleton(R.layout.item_alert, 5)
            swipeRefresh.setOnRefreshListener {
                alertViewModel.viewCreated()
            }
        }
    }

    private fun setupObserver() {
        val alertObserver = Observer<AlertViewModel.UiState> { uiState ->
            uiState.alert?.let {
                binData(it)
            }
            uiState.errorApp?.let {
                val error = ErrorAppFactory(requireContext()).build(it, {
                    alertViewModel.viewCreated()
                })
                binding.errorAppView.render(error)
            } ?: run {
                binding.errorAppView.hide()
            }
            if (uiState.isLoading) {
                if (binding.swipeRefresh.isRefreshing) {
                    //binding.swipeRefresh.setProgressViewOffset(false,0,250)
                    //binding.swipeRefresh.isRefreshing = true
                    skeleton.showOriginal()
                } else {
                    skeleton.showSkeleton()
                }
            } else {
                skeleton.showOriginal()
                binding.swipeRefresh.isRefreshing = false
            }
        }
        alertViewModel.uiState.observe(viewLifecycleOwner, alertObserver)
    }

    private fun binData(alert: List<Sensor>) {
        alertAdapter.submitList(alert)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}