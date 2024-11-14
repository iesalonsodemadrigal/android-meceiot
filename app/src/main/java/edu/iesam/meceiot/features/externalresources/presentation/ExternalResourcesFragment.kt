package edu.iesam.meceiot.features.externalresources.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import edu.iesam.meceiot.databinding.FragmentExternalResourcesBinding
import edu.iesam.meceiot.features.externalresources.domain.ExternalResources
import edu.iesam.meceiot.features.externalresources.presentation.adapter.ExternalResourcesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class ExternalResourcesFragment : BottomSheetDialogFragment() {
    private val ExternalResourcesViewModel: ExternalResourcesViewModel by viewModel()


    private var _binding: FragmentExternalResourcesBinding? = null
    private val binding get() = _binding!!
    private val externalResourcesAdapter = ExternalResourcesAdapter()

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
        ExternalResourcesViewModel.viewCreated()
    }

    private fun setupObserver() {
        val resourcesObserver = Observer<ExternalResourcesViewModel.UiStage> { uiState ->
            uiState.externalResources?.let {
                bindData(it)
            }
        }
        ExternalResourcesViewModel.uiState.observe(viewLifecycleOwner, resourcesObserver)
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

    private fun bindData(ExternalResources: List<ExternalResources>) {
        externalResourcesAdapter.submitList(ExternalResources.sortedBy { it.resourceName })
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}