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

        setItemText(ItemIotBinding.bind(binding.item1.root), R.string.title_item_1, R.string.description_item_1)
        setItemText(ItemIotBinding.bind(binding.item2.root), R.string.title_item_2, R.string.description_item_2)
        setItemText(ItemIotBinding.bind(binding.item3.root), R.string.title_item_3, R.string.description_item_3)
        setItemText(ItemIotBinding.bind(binding.item4.root), R.string.title_item_4, R.string.description_item_4)
        setItemText(ItemIotBinding.bind(binding.item5.root), R.string.title_item_5, R.string.description_item_5)

        return binding.root
    }

    private fun setItemText(itemBinding: ItemIotBinding, titleId: Int, descriptionId: Int) {
        itemBinding.itemTitle.setText(titleId)
        itemBinding.itemDescription.setText(descriptionId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}