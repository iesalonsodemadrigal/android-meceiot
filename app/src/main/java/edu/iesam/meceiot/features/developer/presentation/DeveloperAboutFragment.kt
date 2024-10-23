package edu.iesam.meceiot.features.developer.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import edu.iesam.meceiot.features.developer.domain.models.Developer

class DeveloperAboutFragment : Fragment() {

    private lateinit var developerFactory: DeveloperFactory
    private lateinit var developerViewModel: DeveloperViewModel


    private var _binding: FragmentAboutDeveloperBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutDeveloperBinding.inflate(inflater, container, false)
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

        if (developer.size >= 6) {
            binding.name1.text = developer[0].name
            binding.image1.loadUrl(developer[0].image)
            binding.description1.text = developer[0].description

            binding.name2.text = developer[1].name
            binding.image2.loadUrl(developer[1].image)
            binding.description2.text = developer[1].description

            binding.name3.text = developer[2].name
            binding.image3.loadUrl(developer[2].image)
            binding.description3.text = developer[2].description

            binding.name4.text = developer[3].name
            binding.image4.loadUrl(developer[3].image)
            binding.description4.text = developer[3].description

            binding.name5.text = developer[4].name
            binding.image5.loadUrl(developer[4].image)
            binding.description5.text = developer[4].description


            binding.name6.text = developer[5].name
            binding.image6.loadUrl(developer[5].image)
            binding.description6.text = developer[5].description





        }
    }
}