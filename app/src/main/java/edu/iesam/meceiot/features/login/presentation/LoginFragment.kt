package edu.iesam.meceiot.features.login.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.android_meceiot.R
import edu.iesam.meceiot.features.login.domain.LoginCredentials

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var loginFactory: LoginFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginFactory = LoginFactory(requireContext())
        viewModel = loginFactory.buildViewModel()

        setupObservers()
        gatherLoginCredentials()

    }

    private fun setupObservers() {
        val loginObserver = Observer<LoginViewModel.LoginUiState> { uiState ->
            uiState.loginCredentials?.let { login ->
                //Permitir usar el boton de login
            }
            uiState.errorApp?.let {
                //Pintar el error
            } ?: run {
                //Ocultar el error
            }

            if (uiState.isLoading) {
                Log.d("@dev", "Cargando...")
            } else {
                Log.d("@dev", "Cargando...")
            }

            uiState.loginResponse?.let { loginResponse ->
                Toast.makeText(
                    requireContext(),
                    "$loginResponse",
                    Toast.LENGTH_LONG,
                ).show()
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, loginObserver)
    }

    private fun gatherLoginCredentials() {
        val usernameEditText: EditText = requireView().findViewById(R.id.username)
        val passwordEditText: EditText = requireView().findViewById(R.id.password)
        val loginButton: Button = requireView().findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val loginCredentials = LoginCredentials(usernameEditText.text.toString(), passwordEditText.text.toString())
            viewModel.postLoginCredentials(loginCredentials)

            //DEBUG: muestra el los credenciales por pantalla
            Toast.makeText(
                requireContext(),
                "$loginCredentials",
                Toast.LENGTH_LONG
            ).show()
        }
    }


}