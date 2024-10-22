package edu.iesam.meceiot.features.lorawan.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import coil3.load
import com.example.android_meceiot.databinding.FragmentAboutLorawanBinding
import edu.iesam.meceiot.features.lorawan.domain.LoraWanInfo

class LoraWanAboutFragment : Fragment() {
    private lateinit var loraWanFactory: LoraWanFactory
    private lateinit var loraWanViewModel: LoraWanViewModel

    private var _binding: FragmentAboutLorawanBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutLorawanBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loraWanFactory = LoraWanFactory(requireContext())
        loraWanViewModel = loraWanFactory.provideGetInfoLoraWan()
        setupObserver()
        loraWanViewModel.viewCreated()
    }

    private fun setupObserver() {
        val loraWanObserver = Observer<LoraWanViewModel.UiState> { uiState ->
            uiState.infoLoraWan?.let {
                bindData(it)
            }
            uiState.errorApp?.let {
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
        loraWanViewModel.uiState.observe(viewLifecycleOwner, loraWanObserver)
    }

    private fun bindData(loraWanInfo: List<LoraWanInfo>) {
        val url = "https://n9.cl/nb8pg"

        binding.titleInfo1.text
        binding.imageInfo1.load(url)
        binding.description4.text

        binding.titleInfo2.text
        binding.imageInfo2.load(url)
        binding.description2.text

        binding.titleInfo3.text
        binding.imageInfo3.load(url)
        binding.description3.text

        binding.titleInfo4.text
        binding.imageInfo4.load(url)
        binding.description4.text
    }
}