package edu.iesam.meceiot.features.externalresources.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_meceiot.R
import com.example.android_meceiot.databinding.FragmentExternalResourcesBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import edu.iesam.meceiot.features.externalresources.domain.ExternalResources
import edu.iesam.meceiot.features.externalresources.presentation.adapter.ExternalResourcesAdapter
import perfetto.protos.UiState


class ExternalResourcesFragment : BottomSheetDialogFragment(){
    private lateinit var resourceFactory: ResourceFactory
    private lateinit var viewModel: ExternalResourcesViewModel

    private var _binding: FragmentExternalResourcesBinding? = null
    private val binding get() = _binding!!

    private val adapter = ExternalResourcesAdapter{ resourceUrl ->
        openUrl(resourceUrl)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExternalResourcesBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        resourceFactory = ResourceFactory(requireContext())
        viewModel = resourceFactory.getExternalResourcesViewModel()
        setupObserver()
        viewModel.viewCreated()
    }
    private fun setupObserver(){
        val resourcesObserver = Observer<ExternalResourcesViewModel.UiStage> { uiState ->
            uiState.externalResources?.let {
                bindData(it)
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, resourcesObserver)
    }
    private fun setupView(){
        binding.apply {
            ExternalResourcesFragmentRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            ExternalResourcesFragmentRecyclerView.adapter = adapter
        }
    }
    private fun bindData(ExternalResources: List<ExternalResources>){
        adapter.submitList(ExternalResources.sortedBy { it.resourceName })
    }
    private fun openUrl(url: String){
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
    override fun onDestroyView(){
        super.onDestroyView()
        _binding = null
    }

}