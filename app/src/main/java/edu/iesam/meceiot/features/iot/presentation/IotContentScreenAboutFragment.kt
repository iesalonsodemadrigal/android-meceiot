package edu.iesam.meceiot.features.iot.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import edu.iesam.meceiot.R
import edu.iesam.meceiot.databinding.FragmentIotContentBinding
import edu.iesam.meceiot.databinding.ViewContentListBinding

class IotContentScreenAboutFragment : Fragment(R.layout.fragment_iot_content) {
    private var _binding: FragmentIotContentBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentIotContentBinding.bind(view)
        bindData()

        setItemText(
            ViewContentListBinding.bind(binding.viewParagraph.root),
            R.string.meceiot_title_1,
            R.string.meceiot_content_1
        )
        setItemText(
            ViewContentListBinding.bind(binding.viewParagraph2.root),
            R.string.meceiot_title_2,
            R.string.meceiot_content_2
        )
    }

    private fun setItemText(itemBinding: ViewContentListBinding, titleId: Int, descriptionId: Int) {
        itemBinding.titleInfo.setText(titleId)
        itemBinding.textInfo.setText(descriptionId)
    }

    private fun bindData() {
        binding.apply {
            /* view_detailed_meceiot.viewTitle.setText(R.string.title_item_meceiot)*/
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}