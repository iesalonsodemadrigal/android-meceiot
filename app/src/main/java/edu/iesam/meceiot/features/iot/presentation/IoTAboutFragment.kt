package edu.iesam.meceiot.features.iot.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import edu.iesam.meceiot.R
import edu.iesam.meceiot.databinding.FragmentIotBinding

class IoTAboutFragment : Fragment(R.layout.fragment_iot) {
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
            viewToolbar.mainToolbar.title = getString(R.string.iot_title)

            viewItemMeceiot.apply {
                viewItemTitle.setText(R.string.title_item_meceiot)
                viewItemDescription.setText(R.string.description_item_meceiot)
                viewItemImage.setImageResource(R.drawable.ic_logo_meceiot)
                root.setOnClickListener {
                    findNavController().navigate(R.id.action_meciot_to_meceiotfragment)
                }
            }

            viewItemAlonso.apply {
                viewItemTitle.setText(R.string.title_item_alonso)
                viewItemDescription.setText(R.string.description_item_iot_alonso)
                viewItemImage.setImageResource(R.drawable.ic_logo_iesam)
                root.setOnClickListener {
                    findNavController().navigate(R.id.action_meciot_to_alonsofragment)
                }
            }

            viewItemInternet.apply {
                viewItemTitle.setText(R.string.title_item_internet)
                viewItemDescription.setText(R.string.description_item_internet)
                //viewItemImage.setImageResource(R.drawable.ic_logo_internet)
            }

            viewItemEnergySaving.apply {
                viewItemTitle.setText(R.string.title_item_energy_saving)
                viewItemDescription.setText(R.string.description_item_energy_saving)
                // viewItemImage.setImageResource(R.drawable.ic_logo_energy_saving)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

/*        _binding = FragmentIotBinding.inflate(inflater, container, false)
        bindData()

        setItemText(
            ItemIotBinding.bind(binding.viewItemMeceiot.root),
            R.string.title_item_meceiot,
            R.string.description_item_meceiot
        )
        setItemText(
            ItemIotBinding.bind(binding.viewItemAlonso.root),
            R.string.title_item_alonso,
            R.string.description_item_iot_alonso
        )
        setItemText(
            ItemIotBinding.bind(binding.viewItemInternet.root),
            R.string.title_item_internet,
            R.string.description_item_internet
        )
        setItemText(
            ItemIotBinding.bind(binding.viewItemEnergySaving.root),
            R.string.title_item_energy_saving,
            R.string.description_item_energy_saving
        )

        setItemImage(
            ItemIotBinding.bind(binding.viewItemMeceiot.root),
            R.drawable.ic_logo_meceiot
        )
        setItemImage(
            ItemIotBinding.bind(binding.viewItemAlonso.root),
            R.drawable.ic_logo_iesam
        )

        return binding.root*/


/*    private fun setItemText(itemBinding: ItemIotBinding, titleId: Int, descriptionId: Int) {
        itemBinding.viewItemTitle.setText(titleId)
        itemBinding.viewItemDescription.setText(descriptionId)
    }

    private fun setItemImage(itemBinding: ItemIotBinding, imageResId: Int) {
        itemBinding.viewItemImage.setImageResource(imageResId)
    }*/

/*    private fun bindData() {
        binding.apply {
            viewToolbar.mainToolbar.title = getString(R.string.iot_title)
        }
    }*/

