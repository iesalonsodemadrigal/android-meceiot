package edu.iesam.meceiot.features.developer.presentation

import DeveloperAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import edu.iesam.meceiot.core.presentation.AppIntent
import edu.iesam.meceiot.databinding.FragmentDeveloperListBinding
import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo

class DeveloperAboutFragment : BottomSheetDialogFragment() {

    private lateinit var developerFactory: DeveloperFactory
    private lateinit var developerAboutViewModel: DeveloperAboutViewModel
    private lateinit var appIntent: AppIntent

    private var _binding: FragmentDeveloperListBinding? = null
    private val binding get() = _binding!!

    private val developerAdapter = DeveloperAdapter { url ->
        appIntent.openUrl(url)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeveloperListBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appIntent = AppIntent(requireContext())

        developerFactory = DeveloperFactory(requireContext())
        developerAboutViewModel = developerFactory.provideGetDevelopers()
        setupObserver()
        developerAboutViewModel.viewDevelopers()
    }

    private fun setupObserver() {
        val developerObserver = Observer<DeveloperAboutViewModel.UiState> { uiState ->
            uiState.infoDeveloper?.let {
                bindData(it)
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
        }
    }

    private fun bindData(developers: List<DeveloperInfo>) {
        developerAdapter.submitList(developers.sortedBy { it.id.toInt() })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
