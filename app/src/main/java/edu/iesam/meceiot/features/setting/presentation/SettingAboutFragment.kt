package edu.iesam.meceiot.features.setting.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import edu.iesam.meceiot.BuildConfig
import edu.iesam.meceiot.R
import edu.iesam.meceiot.core.presentation.AppIntent
import edu.iesam.meceiot.databinding.FragmentSettingsBinding
import edu.iesam.meceiot.databinding.ItemSettingBinding
import edu.iesam.meceiot.features.setting.presentation.logout.LogoutDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingAboutFragment : Fragment(R.layout.fragment_settings) {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private lateinit var intent: AppIntent

    val viewModel: SettingsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        intent = AppIntent(requireContext())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        binding.logoutButton.setOnClickListener {
            showLogoutConfirmationDialog()
        }
    }

    private fun setUpView() {
        configureItem(
            binding.itemDeveloper, R.string.title_developers, R.string.text_developers
        ) {
            findNavController().navigate(R.id.action_settings_to_developerAboutFragment)
        }

        configureItem(
            binding.itemResources, R.string.title_resources, R.string.text_resources
        ) {
            findNavController().navigate(R.id.action_settings_to_externalResourcesFragment)
        }

        configureItem(
            binding.itemWeb, R.string.title_web, R.string.text_web
        ) {
            intent.openUrl(getString(R.string.link_web))
        }

        configureItem(
            binding.itemEmail, R.string.title_email, R.string.text_email
        ) {
            intent.openEmail(getString(R.string.text_email))
        }

        configureItem(
            binding.itemPlaystore, R.string.title_store, R.string.text_appstore
        ) {
            intent.shareApp(getString(R.string.link_appstore))
        }

        configureItem(
            binding.itemLegalWarning, R.string.title_legal_warning, R.string.legal_warning_text
        ) {
            intent.openUrl(getString(R.string.link_legal_warning))
        }

        binding.itemVersion.textTitle.text = getString(R.string.text_version)
        binding.itemVersion.textSubtitle.text = BuildConfig.VERSION_NAME
        binding.itemVersion.settingItem.setOnClickListener {
            intent.openUrl(getString(R.string.link_appstore))
        }
    }

    private fun configureItem(
        item: ItemSettingBinding, titleRes: Int, subtitleRes: Int, onClick: () -> Unit
    ) {
        item.textTitle.text = getString(titleRes)
        item.textSubtitle.text = getString(subtitleRes)
        item.settingItem.setOnClickListener { onClick() }
    }

    private fun showLogoutConfirmationDialog() {
        LogoutDialog().apply {
            setListener(object : LogoutDialog.LogoutListener {
                override fun onLogoutConfirmed() {
                    viewModel.logout()
                    navigateToLogin()
                }
            })
        }.show(parentFragmentManager, "LogoutDialog")
    }

    private fun navigateToLogin() {
        findNavController().navigate(
            SettingAboutFragmentDirections.actionSettingsToLoginFragment(),
            NavOptions.Builder().setPopUpTo(R.id.fragment_settings, true).build()
        )
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
