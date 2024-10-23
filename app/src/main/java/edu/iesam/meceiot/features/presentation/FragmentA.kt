package edu.iesam.meceiot.features.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.android_meceiot.R
import com.google.android.material.appbar.MaterialToolbar

class FragmentA : Fragment(R.layout.fragment_a) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        view?.findViewById<MaterialToolbar>(R.id.main_menu)?.apply {
            setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
        return view
    }
}