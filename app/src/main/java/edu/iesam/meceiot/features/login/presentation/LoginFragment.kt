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
import com.example.android_meceiot.databinding.FragmentLoginBinding
import edu.iesam.meceiot.features.login.domain.LoginCredentials

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var loginFactory: LoginFactory

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginFactory = LoginFactory(requireContext())
        viewModel = loginFactory.buildViewModel()

        setupObservers()
        setLoginForm()

    }

    private fun setupObservers() {
        val loginObserver = Observer<LoginViewModel.LoginUiState> { uiState ->
            uiState.loginCredentials?.let { loginCredentials ->
                //DEBUG: muestra el los credenciales por pantalla
                Log.d("@dev", "Login credentials: $loginCredentials")
                Toast.makeText(
                    requireContext(),
                    "$loginCredentials",
                    Toast.LENGTH_LONG,
                ).show()
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

            uiState.loginSuccessful.let { loginSuccess ->
                //DEBUG: muestra el response por pantalla
                Log.d("@dev", "Login: $loginSuccess")
                Toast.makeText(
                    requireContext(),
                    "$loginSuccess",
                    Toast.LENGTH_LONG,
                ).show()
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, loginObserver)
    }

    private fun setLoginForm() {
        val usernameEditText: EditText = requireView().findViewById(R.id.username)
        val passwordEditText: EditText = requireView().findViewById(R.id.password)
        val loginButton: Button = requireView().findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val loginCredentials =
                LoginCredentials(usernameEditText.text.toString(), passwordEditText.text.toString())
            viewModel.postLoginCredentials(loginCredentials)
        }
    }

}