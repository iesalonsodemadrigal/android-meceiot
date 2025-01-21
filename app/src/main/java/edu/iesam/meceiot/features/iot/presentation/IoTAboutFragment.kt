package edu.iesam.meceiot.features.iot.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import edu.iesam.meceiot.R
import edu.iesam.meceiot.databinding.FragmentIotBinding
import edu.iesam.meceiot.databinding.ItemIotBinding

class IoTFragment : Fragment() {

    private var _binding: FragmentIotBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIotBinding.inflate(inflater, container, false)

        bindData()

        return binding.root
    }

    private fun bindData() {
        binding.apply {
            viewToolbar.viewToolbarTitle.title = getString(R.string.iot_title)

            bindItemData(
                ItemIotBinding.bind(viewItemMeceiot.root),
                R.string.title_item_meceiot,
                R.string.description_item_meceiot,
                R.drawable.ic_logo_meceiot
            )
            bindItemData(
                ItemIotBinding.bind(viewItemAlonso.root),
                R.string.title_item_alonso,
                R.string.description_item_iot_alonso,
                R.drawable.ic_logo_iesam
            )
            bindItemData(
                ItemIotBinding.bind(viewItemInternet.root),
                R.string.title_item_internet,
                R.string.description_item_internet,
                0
            )
            bindItemData(
                ItemIotBinding.bind(viewItemEnergySaving.root),
                R.string.title_item_energy_saving,
                R.string.description_item_energy_saving,
                0 // No image resource
            )
        }
    }

    private fun bindItemData(
        itemBinding: ItemIotBinding,
        titleId: Int,
        descriptionId: Int,
        imageResId: Int
    ) {
        itemBinding.viewItemTitle.setText(titleId)
        itemBinding.viewItemDescription.setText(descriptionId)
        if (imageResId != 0) {
            itemBinding.viewItemImage.setImageResource(imageResId)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}