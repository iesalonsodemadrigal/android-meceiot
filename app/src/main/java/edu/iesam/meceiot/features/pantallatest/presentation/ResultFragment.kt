package edu.iesam.meceiot.features.pantallatest.presentation

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import edu.iesam.meceiot.databinding.DialogResultBinding
import edu.iesam.meceiot.databinding.FragmentResultBinding

class ResultDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = DialogResultBinding.inflate(layoutInflater)

        val selectedOptionsText = arguments?.getString("selectedOptionsText") ?: ""
        val correctCount = arguments?.getInt("correctCount") ?: 0

        binding.selectedOptionsTextView.text = selectedOptionsText
        binding.correctCountTextView.text = "Aciertos: $correctCount"

        return AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .create()
    }
}

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