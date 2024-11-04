package edu.iesam.meceiot.features.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.android_meceiot.R
import edu.iesam.meceiot.features.developer.presentation.DeveloperAboutFragment

class FragmentB : Fragment(R.layout.fragment_b) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_b, container, false)

        val navigateButton: Button = view.findViewById(R.id.button_navigate)
        navigateButton.setOnClickListener {
            // Usar NavController para navegar
            val navController = findNavController()
            navController.navigate(R.id.action_fragmentB_to_developerAboutFragment) // Asegúrate de tener la acción definida en tu navigation graph
        }

        return view
    }
}

