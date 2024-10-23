package edu.iesam.meceiot.features.developer.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.android_meceiot.databinding.FragmentDeveloperListBinding
import edu.iesam.meceiot.features.developer.domain.models.Developer

class DeveloperAboutFragment : Fragment() {

    private lateinit var developerFactory: DeveloperFactory
    private lateinit var developerViewModel: DeveloperViewModel


    private var _binding: FragmentDeveloperListBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeveloperListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        developerFactory = DeveloperFactory(requireContext())
        developerViewModel = developerFactory.provideGetDevelopersUseCase()
        setupObserver()
        developerViewModel.viewDevelopers()
    }

    private fun setupObserver() {
        val developerObserver = Observer<DeveloperViewModel.UiState> { uiState ->
            uiState.infoDeveloper?.let {
                bindData(it)
            }
            uiState.errorMessage?.let {
                //print error
            } ?: run {
                // hide error
            }
            if (uiState.isLoading) {
                //show loading
            } else {
                //hide loading
            }
        }
        developerViewModel.uiState.observe(viewLifecycleOwner, developerObserver)
    }

    private fun bindData(developer: List<Developer>) {

    }
}