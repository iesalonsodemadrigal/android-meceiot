package edu.iesam.meceiot.features.lorawan.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

class LoraWanAboutFragment: Fragment() {
    private lateinit var loraWanFactory: LoraWanFactory
    private lateinit var loraWanViewModel: LoraWanViewModel

    private var _binding: FragmentLoraWanBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoraWanBinding.inflate(inflater, container, false)
        //setupView()
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loraWanFactory = LoraWanFactory(requireContext())
        loraWanViewModel = loraWanFactory.provideGetInfoLoraWan()

        loraWanViewModel.viewCreated()
    }
}