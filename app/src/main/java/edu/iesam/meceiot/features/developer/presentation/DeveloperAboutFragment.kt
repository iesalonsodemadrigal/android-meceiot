package edu.iesam.meceiot.features.developer.presentation

import DeveloperAdapter
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
import edu.iesam.meceiot.databinding.FragmentDeveloperListBinding
import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeveloperAboutFragment : BottomSheetDialogFragment() {

    private val developerAboutViewModel: DeveloperAboutViewModel by viewModel()
    private lateinit var appIntent: AppIntent

    private var _binding: FragmentDeveloperListBinding? = null
    private val binding get() = _binding!!
    private lateinit var skeleton: Skeleton

    private val developerAdapter = DeveloperAdapter { url -> appIntent.openUrl(url) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeveloperListBinding.inflate(inflater, container, false)
        appIntent = AppIntent(requireContext())
        setupView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        developerAboutViewModel.viewDevelopers()
    }

    private fun setupObserver() {
        val developerObserver =  Observer<DeveloperAboutViewModel.UiState>{ uiState ->
            uiState.infoDeveloper?.let {
                bindData(it)
            }
            uiState.errorMessage?.let {
                val error = ErrorAppFactory(requireContext())
                val errorAppUI = error.build(it)
                binding.errorAppView.render(errorAppUI)
            } ?: run {
                binding.errorAppView.hide()
            }
            if (uiState.isLoading) {
                skeleton.showSkeleton()
            } else {
                skeleton.showOriginal()
            }
        }
        developerAboutViewModel.uiState.observe(viewLifecycleOwner, developerObserver)
    }


    private fun setupView() {
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.VERTICAL, false
            )
            recyclerView.adapter = developerAdapter
            skeleton = recyclerView.applySkeleton(R.layout.item_developer)
        }
    }

    private fun bindData(developers: List<DeveloperInfo>) {
        developerAdapter.submitList(developers)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}