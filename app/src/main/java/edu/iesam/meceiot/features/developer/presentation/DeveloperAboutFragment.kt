package edu.iesam.meceiot.features.developer.presentation

import DeveloperAdapter
import android.content.Intent
import android.net.Uri
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

    private val developerAdapter = DeveloperAdapter { url ->
        openUrl(url)
    }

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
    }

    private fun setupObserver() {
        val developerObserver = Observer<DeveloperViewModel.UiState> { uiState ->
            uiState.infoDeveloper?.let {
                bindData(it)
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

    private fun bindData(developers: List<DeveloperInfo>) {

        developerAdapter.submitList(developers.sortedBy { it.id.toInt() })
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
