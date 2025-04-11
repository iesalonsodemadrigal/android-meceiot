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
import edu.iesam.meceiot.core.domain.ErrorApp
import edu.iesam.meceiot.core.presentation.hide
import edu.iesam.meceiot.core.presentation.views.ErrorAppFactory
import edu.iesam.meceiot.databinding.FragmentAlertBinding
import edu.iesam.meceiot.features.alerts.domain.Alert
import edu.iesam.meceiot.features.alerts.presentation.adapter.AlertAdapter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlertFragment : Fragment() {

    private var _binding: FragmentAlertBinding? = null
    private val binding get() = _binding!!
    private val alertViewModel: AlertViewModel by viewModel()
    private val alertAdapter = AlertAdapter()
    private lateinit var skeleton: Skeleton
    private val errorFactory: ErrorAppFactory by inject()

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
        alertViewModel.fetchAlerts()
    }

    private fun setupView() {
        binding.apply {
            mainToolbarAlerts.mainToolbar.title = getString(R.string.alerts_title)

            alertsRecyclerview.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            alertsRecyclerview.adapter = alertAdapter
            skeleton = alertsRecyclerview.applySkeleton(R.layout.item_alert, 10)

            swipeRefreshLayout.setColorSchemeResources(
                R.color.md_theme_primary,
                R.color.md_theme_secondary,
                R.color.md_theme_tertiary
            )

            swipeRefreshLayout.setOnRefreshListener {
                alertViewModel.fetchAlerts()
            }
        }
    }

    private fun setupObserver() {
        val alertObserver = Observer<AlertViewModel.UiState> { uiState ->
            checkLoading(uiState.isLoading)
            checkError(uiState.errorApp)
            bindData(uiState.alert)
        }
        alertViewModel.uiState.observe(viewLifecycleOwner, alertObserver)
    }

    private fun checkLoading(isLoading: Boolean) {
        if (isLoading) {
            skeleton.showSkeleton()
        } else {
            skeleton.showOriginal()

            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun checkError(errorApp: ErrorApp?) {
        errorApp?.let { errorApp ->
            val errorAppUi = errorFactory.build(errorApp) {
                alertViewModel.fetchAlerts()
            }
            binding.errorAppView.render(errorAppUi)
        } ?: run {
            binding.errorAppView.hide()
        }
    }

    private fun bindData(alerts: List<Alert>?) {
        alerts?.let {
            alertAdapter.submitList(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
