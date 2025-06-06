package edu.iesam.meceiot.features.externalresources.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import edu.iesam.meceiot.R
import edu.iesam.meceiot.core.presentation.AppIntent
import edu.iesam.meceiot.core.presentation.hide
import edu.iesam.meceiot.core.presentation.views.ErrorAppFactory
import edu.iesam.meceiot.databinding.FragmentExternalResourcesBinding
import edu.iesam.meceiot.features.externalresources.domain.ExternalResources
import edu.iesam.meceiot.features.externalresources.presentation.adapter.ExternalResourcesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExternalResourcesFragment : BottomSheetDialogFragment() {

    private val externalResourcesViewModel: ExternalResourcesViewModel by viewModel()
    private var _binding: FragmentExternalResourcesBinding? = null
    private val binding get() = _binding!!
    private val externalResourcesAdapter = ExternalResourcesAdapter()
    private lateinit var appIntent: AppIntent
    private lateinit var skeleton: Skeleton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExternalResourcesBinding.inflate(inflater, container, false)
        setupView()
        appIntent = AppIntent(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        externalResourcesViewModel.viewCreated()

    }

    private fun setupView() {
        binding.apply {
            recyclerViewExternalResource.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = externalResourcesAdapter
                skeleton = applySkeleton(R.layout.item_external_resources)
                externalResourcesAdapter.setEvent { url -> openUrl(url) }
            }
        }
    }

    private fun setupObserver() {
        val externalResourcesObserver = Observer<ExternalResourcesViewModel.UiState> { uiState ->
            uiState.externalResources?.let { bindData(it) }

            uiState.errorApp?.let {
                val errorAppUI = ErrorAppFactory(requireContext()).build(it, {
                    externalResourcesViewModel.viewCreated()
                })
                binding.errorAppViewResources.render(errorAppUI)
            } ?: run {
                binding.errorAppViewResources.hide()
            }
            if (uiState.loading) {
                skeleton.showSkeleton()
            } else {
                skeleton.showOriginal()
            }

        }
        externalResourcesViewModel.uiState.observe(viewLifecycleOwner, externalResourcesObserver)
    }

    private fun bindData(externalResources: List<ExternalResources>) {
        externalResourcesAdapter.submitList(externalResources.sortedBy { it.author })
    }


    private fun openUrl(url: String) {
        appIntent.openUrl(url)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
