package edu.iesam.meceiot.features.pantallatest.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import edu.iesam.meceiot.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val selectedOptionsText = arguments?.getString("selectedOptionsText") ?: ""
        val correctCount = arguments?.getInt("correctCount") ?: 0

        binding.selectedOptionsTextView.text = selectedOptionsText
        binding.correctCountTextView.text = "Aciertos: $correctCount"
    }
}