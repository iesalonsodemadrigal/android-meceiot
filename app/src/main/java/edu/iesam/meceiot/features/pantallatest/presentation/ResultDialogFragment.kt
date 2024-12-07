package edu.iesam.meceiot.features.pantallatest.presentation

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import edu.iesam.meceiot.databinding.DialogResultBinding

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