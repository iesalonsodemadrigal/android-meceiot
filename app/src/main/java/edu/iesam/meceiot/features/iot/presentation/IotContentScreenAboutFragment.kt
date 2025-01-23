package edu.iesam.meceiot.features.iot.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import edu.iesam.meceiot.R
import edu.iesam.meceiot.databinding.FragmentIotContentBinding
import edu.iesam.meceiot.databinding.ItemContentListBinding

class IotContentScreenAboutFragment : Fragment(R.layout.fragment_iot_content) {
    private var _binding: FragmentIotContentBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentIotContentBinding.bind(view)
        bindData()

        setItemText(
            ItemContentListBinding.bind(binding.viewParagraph.root),
            R.string.meceiot_title_1,
            R.string.meceiot_content_1
        )
        setItemText(
            ItemContentListBinding.bind(binding.viewParagraph2.root),
            R.string.meceiot_title_2,
            R.string.meceiot_content_2
        )
        setItemText(
            ItemContentListBinding.bind(binding.viewParagraph3.root),
            R.string.meceiot_title_3,
            R.string.meceiot_content_3
        )
        setItemText(
            ItemContentListBinding.bind(binding.viewParagraph4.root),
            R.string.meceiot_title_4,
            R.string.meceiot_content_4
        )
    }

    private fun setItemText(itemBinding: ItemContentListBinding, titleId: Int, descriptionId: Int) {
        itemBinding.titleInfo.setText(titleId)
        itemBinding.textInfo.setText(descriptionId)
    }

    private fun bindData() {
        binding.apply {
            viewDetailedMeceiot.mainToolbar.title = getString(R.string.iot_title)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}