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
import edu.iesam.meceiot.MainActivity
import edu.iesam.meceiot.R
import edu.iesam.meceiot.core.presentation.AppIntent
import edu.iesam.meceiot.core.presentation.hide
import edu.iesam.meceiot.core.presentation.views.ErrorAppFactory
import edu.iesam.meceiot.databinding.FragmentDeveloperListBinding
import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo
import edu.iesam.meceiot.features.setting.presentation.SettingAboutFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeveloperAboutFragment : BottomSheetDialogFragment() {

    private val developerAboutViewModel: DeveloperAboutViewModel by viewModel()
    private var _binding: FragmentDeveloperListBinding? = null
    private val binding get() = _binding!!
    private val developerAdapter = DeveloperAdapter()
    private val appIntent: AppIntent by lazy { AppIntent(requireContext()) }
    private lateinit var skeleton: Skeleton
    val currentScreen = arguments?.getString("Developer")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDeveloperListBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        developerAboutViewModel.viewDevelopers()
    }

    private fun setupView() {
        binding.apply {
            recyclerViewDeveloper.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = developerAdapter
                skeleton = applySkeleton(R.layout.item_developer)
                developerAdapter.setEvent { url -> openUrl(url) }
            }
        }
    }

    private fun setupObserver() {
        val developerObserver = Observer<DeveloperAboutViewModel.UiState> { uiState ->
            uiState.infoDeveloper?.let { bindData(it) }

            uiState.errorMessage?.let {
                val error = ErrorAppFactory(requireContext())
                val errorAppUI = error.build(it)

                val retryActivity = when (currentScreen) {
                    "Developer" -> DeveloperAboutFragment::class.java
                    "Text" -> SettingAboutFragment::class.java
                    else -> MainActivity::class.java
                }
                errorAppUI.getActionRetry(retryActivity)
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

    private fun bindData(developers: List<DeveloperInfo>) {
        developerAdapter.submitList( developers.sortedBy { it.id.toIntOrNull() ?: Int.MAX_VALUE })
    }


    private fun openUrl(url: String) {
        appIntent.openUrl(url)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
