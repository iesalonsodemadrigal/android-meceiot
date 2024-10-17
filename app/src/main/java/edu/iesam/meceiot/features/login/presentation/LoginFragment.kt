package edu.iesam.meceiot.features.login.presentation

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.android_meceiot.R

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
        }
        viewModel.uiState.observe(viewLifecycleOwner, loginObserver)
    }


}