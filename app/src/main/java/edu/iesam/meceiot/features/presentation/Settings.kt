package edu.iesam.meceiot.features.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import edu.iesam.meceiot.R


class Settings : Fragment(R.layout.fragment_settings) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        val navigateButton: Button = view.findViewById(R.id.button_navigate)
        navigateButton.setOnClickListener {
            val action = FragmentBDirections.actionFragmentBToDeveloperAboutFragment()
            findNavController().navigate(action)
        }
        return view
    }
}