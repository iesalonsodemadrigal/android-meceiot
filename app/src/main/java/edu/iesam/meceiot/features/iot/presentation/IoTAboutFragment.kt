package edu.iesam.meceiot.features.iot.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import edu.iesam.meceiot.R
import edu.iesam.meceiot.databinding.FragmentIotBinding

class IoTAboutFragment : Fragment() {
    private var _binding: FragmentIotBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIotBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    private fun setupView() {
        binding.apply {
            viewCollapseToolbar.viewToolbar.title = getString(R.string.iot_title)

            viewItemMeceiot.apply {
                viewItemTitle.setText(R.string.title_item_meceiot)
                viewItemDescription.setText(R.string.description_item_meceiot)
                viewItemImage.setImageResource(R.drawable.ic_logo_meceiot)
                root.setOnClickListener {
                    val action = IoTAboutFragmentDirections.actionIotFragmentToIotDetailFragment(
                        layoutResId = R.layout.view_iot_meceiot,
                        titleResId = R.string.title_item_meceiot
                    )
                    findNavController().navigate(action)
                }
            }

            viewItemAlonso.apply {
                viewItemTitle.setText(R.string.title_item_alonso)
                viewItemDescription.setText(R.string.description_item_iot_alonso)
                viewItemImage.setImageResource(R.drawable.ic_logo_iesam)
                root.setOnClickListener {
                    val action = IoTAboutFragmentDirections.actionIotFragmentToIotDetailFragment(
                        layoutResId = R.layout.view_iot_alonso,
                        titleResId = R.string.title_item_alonso
                    )
                    findNavController().navigate(action)
                }
            }

            viewItemInternet.apply {
                viewItemTitle.setText(R.string.title_item_internet)
                viewItemDescription.setText(R.string.description_item_internet)
                viewItemImage.setImageResource(R.drawable.ic_logo_iot)
                root.setOnClickListener {
                    val action = IoTAboutFragmentDirections.actionIotFragmentToIotDetailFragment(
                        layoutResId = R.layout.view_iot_internet_of_things,
                        titleResId = R.string.title_item_internet
                    )
                    findNavController().navigate(action)
                }
            }

            viewItemLora.apply {
                viewItemTitle.setText(R.string.title_item_lora)
                viewItemDescription.setText(R.string.description_item_lora)
                viewItemImage.setImageResource(R.drawable.ic_generic_sensor)
                root.setOnClickListener {
                    val action = IoTAboutFragmentDirections.actionIotFragmentToIotDetailFragment(
                        layoutResId = R.layout.view_iot_lora,
                        titleResId = R.string.title_item_lora
                    )
                    findNavController().navigate(action)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


