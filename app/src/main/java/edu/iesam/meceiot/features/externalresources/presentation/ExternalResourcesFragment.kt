package edu.iesam.meceiot.features.externalresources.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import edu.iesam.meceiot.core.presentation.AppIntent
import edu.iesam.meceiot.databinding.FragmentExternalResourcesBinding
import edu.iesam.meceiot.features.externalresources.domain.ExternalResources
import edu.iesam.meceiot.features.externalresources.presentation.adapter.ExternalResourcesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class ExternalResourcesFragment : BottomSheetDialogFragment() {
    private val externalResourcesViewModel: ExternalResourcesViewModel by viewModel()

    private var _binding: FragmentExternalResourcesBinding? = null
    private val binding get() = _binding!!
    private val externalResourcesAdapter = ExternalResourcesAdapter()
    private val appIntent: AppIntent by lazy { AppIntent(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExternalResourcesBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        showSkeleton()
        externalResourcesViewModel.viewCreated()

    }

    private fun setupView() {
        binding.apply {
            ExternalResourcesFragmentRecyclerView.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                externalResourcesAdapter.setEvent { url -> openUrl(url) }
                adapter = externalResourcesAdapter
            }
        }
    }

    private fun setupObserver() {
        val resourcesObserver = Observer<ExternalResourcesViewModel.UiState> { uiState ->
            uiState.externalResources?.let {
                hideSkeleton()
                bindData(it)
            }
        }
        externalResourcesViewModel.uiState.observe(viewLifecycleOwner, resourcesObserver)
    }

    private fun showSkeleton() {
        binding.skeletonLayout.showSkeleton()
    }

    private fun hideSkeleton() {
        binding.skeletonLayout.showOriginal()
    }

    private fun bindData(ExternalResources: List<ExternalResources>) {
        externalResourcesAdapter.submitList(ExternalResources.sortedBy { it.author })
    }

    private fun openUrl(url: String) {
        appIntent.openUrl(url)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}