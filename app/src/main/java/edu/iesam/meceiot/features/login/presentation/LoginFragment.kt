package edu.iesam.meceiot.features.login.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import edu.iesam.meceiot.databinding.FragmentLoginBinding
import edu.iesam.meceiot.features.login.domain.LoginCredentials
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    val viewModel: LoginViewModel by viewModel()

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
        setupObservers()
        viewModel.checkUserLoggedIn()
        setLoginForm()
    }

    private fun setupObservers() {
        val loginObserver = Observer<LoginViewModel.LoginUiState> { uiState ->
            if (uiState.userLoggedIn) {
                // Check if we're on the login fragment before navigating
                if (findNavController().currentDestination?.id == edu.iesam.meceiot.R.id.login_fragment) {
                    findNavController().navigate(
                        LoginFragmentDirections.actionLoginFragmentToMainFragment()
                    )
                }
            }
            uiState.errorApp?.let {
                //Pintar el error? O mostrar un toast de que no hay conexion quizá
            } ?: run {
            }
            if (uiState.isLoading) {
                //Mostrar un loading o algo?
                Log.d("@dev", "Cargando...")
            } else {
                Log.d("@dev", "Cargando...")
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, loginObserver)
    }

    private fun setLoginForm() {
        val usernameEditText: EditText = binding.username
        val passwordEditText: EditText = binding.password
        val loginButton: Button = binding.loginButton

        loginButton.setOnClickListener {
            val loginCredentials =
                LoginCredentials(usernameEditText.text.toString(), passwordEditText.text.toString())
            viewModel.postLoginCredentials(loginCredentials)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}