package edu.iesam.meceiot.features.developer.presentation

import DeveloperAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_meceiot.databinding.FragmentDeveloperListBinding
import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo

class DeveloperAboutFragment : Fragment() {

    private lateinit var developerFactory: DeveloperFactory
    private lateinit var developerViewModel: DeveloperViewModel

    private var _binding: FragmentDeveloperListBinding? = null
    private val binding get() = _binding!!

    private val developerAdapter = DeveloperAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeveloperListBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        developerFactory = DeveloperFactory(requireContext())
        developerViewModel = developerFactory.provideGetDevelopers()
        setupObserver()
        developerViewModel.viewDevelopers()

        // Configura el botón para salir
        binding.buttonExit.setOnClickListener {
            findNavController().navigateUp()
        }

        // Configura el botón para abrir la URL
        binding.buttonOpenUrl.setOnClickListener {
            openUrlInBrowser("https://www.ejemplo.com")
        }
    }

    private fun setupObserver() {
        val developerObserver = Observer<DeveloperViewModel.UiState> { uiState ->
            uiState.infoDeveloper?.let {
                bindData(it)
            }
            uiState.errorMessage?.let {} ?: run {}
            if (uiState.isLoading) {
                // Mostrar progreso si es necesario
            } else {
                // Ocultar progreso si es necesario
            }
        }
        developerViewModel.uiState.observe(viewLifecycleOwner, developerObserver)
    }

    private fun setupView() {
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.VERTICAL, false
            )
            recyclerView.adapter = developerAdapter
        }
    }

    private fun bindData(developer: List<DeveloperInfo>) {
        developerAdapter.submitList(developer)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
