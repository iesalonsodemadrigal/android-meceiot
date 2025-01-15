package edu.iesam.meceiot.features.setting.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import edu.iesam.meceiot.BuildConfig
import edu.iesam.meceiot.R
import edu.iesam.meceiot.core.presentation.AppIntent
import edu.iesam.meceiot.databinding.FragmentSettingsBinding

class SettingAboutFragment : Fragment(R.layout.fragment_settings) {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private lateinit var intent: AppIntent

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        intent = AppIntent(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            itemDeveloper.textTitle.text = getString(R.string.title_developers)
            itemDeveloper.textSubtitle.text = getString(R.string.text_developers)

            itemResources.textTitle.text = getString(R.string.title_resources)
            itemResources.textSubtitle.text = getString(R.string.text_resources)

            itemWeb.textTitle.text = getString(R.string.title_web)
            itemWeb.textSubtitle.text = getString(R.string.text_web)
            itemWeb.settingItem.setOnClickListener {
                intent.openUrl(getString(R.string.link_web))
            }

            itemEmail.textTitle.text = getString(R.string.title_email)
            itemEmail.textSubtitle.text = getString(R.string.text_email)
            itemEmail.settingItem.setOnClickListener {
                intent.openEmail(getString(R.string.text_email))
            }

            itemPlaystore.textTitle.text = getString(R.string.title_store)
            itemPlaystore.textSubtitle.text = getString(R.string.text_appstore)
            itemPlaystore.settingItem.setOnClickListener {
                intent.shareApp(getString(R.string.link_appstore))
            }

            itemLegalWarning.textTitle.text = getString(R.string.title_legal_warning)
            itemLegalWarning.textSubtitle.text = getString(R.string.legal_warning_text)
            itemLegalWarning.settingItem.setOnClickListener {
                intent.openUrl(getString(R.string.link_legal_warning))
            }

            itemVersion.textTitle.text = getString(R.string.text_version)
            val appVersion = BuildConfig.VERSION_NAME
            itemVersion.textSubtitle.text = appVersion
            itemVersion.settingItem.setOnClickListener{
                intent.openUrl(getString(R.string.link_appstore))
            }
        }

        binding.itemDeveloper.settingItem.setOnClickListener {
            findNavController().navigate(R.id.action_settings_to_developerAboutFragment)
        }

        binding.itemResources.settingItem.setOnClickListener {
            findNavController().navigate(R.id.action_settings_to_externalResourcesFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
