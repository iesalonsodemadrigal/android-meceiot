package edu.iesam.meceiot.features.lorawan.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.android_meceiot.databinding.FragmentAboutLorawanBinding
import edu.iesam.meceiot.core.extensions.loadUrl
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
        val url = "https://alfaiot.com/wp-content/uploads/2022/11/LoRaWAN_Logo.svg_.png"
        //una foto de prueba

        // Asegurarse de que el tamaño de la lista sea el esperado antes de asignar
        if (loraWanInfo.size >= 4) {
            binding.titleInfo1.text = loraWanInfo[0].title
            binding.imageInfo1.loadUrl(url)
            binding.description1.text = loraWanInfo[0].description

            binding.titleInfo2.text = loraWanInfo[1].title
            binding.imageInfo2.loadUrl(url)
            binding.description2.text = loraWanInfo[1].description

            binding.titleInfo3.text = loraWanInfo[2].title
            binding.imageInfo3.loadUrl(url)
            binding.description3.text = loraWanInfo[2].description

            binding.titleInfo4.text = loraWanInfo[3].title
            binding.imageInfo4.loadUrl(url)
            binding.description4.text = loraWanInfo[3].description
        }
    }
}