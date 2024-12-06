package edu.iesam.meceiot.features.developer.presentation

import DeveloperAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import edu.iesam.meceiot.core.presentation.AppIntent
import edu.iesam.meceiot.databinding.FragmentDeveloperListBinding
import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeveloperAboutFragment : BottomSheetDialogFragment() {


    private val developerAboutViewModel: DeveloperAboutViewModel by viewModel()
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
        developerAboutViewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            uiState.infoDeveloper?.let {
                bindData(it)
            }
        }
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
        developerAdapter.submitList(developers)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
