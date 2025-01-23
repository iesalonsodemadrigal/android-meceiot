package edu.iesam.meceiot.features.iot.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import edu.iesam.meceiot.R
import edu.iesam.meceiot.databinding.FragmentIotContentBinding
import edu.iesam.meceiot.databinding.ItemContentListBinding

class IotContentScreenAboutFragment : Fragment(R.layout.fragment_iot_content) {
    private var _binding: FragmentIotContentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIotContentBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    private fun setupView() {
        binding.apply {
            viewDetailedMeceiot.mainToolbar.title = getString(R.string.iot_title)

            setItemText(
                ItemContentListBinding.bind(viewParagraph.root),
                R.string.meceiot_title_1,
                R.string.meceiot_content_1
            )
            setItemText(
                ItemContentListBinding.bind(viewParagraph2.root),
                R.string.meceiot_title_2,
                R.string.meceiot_content_2
            )
            setItemText(
                ItemContentListBinding.bind(viewParagraph3.root),
                R.string.meceiot_title_3,
                R.string.meceiot_content_3
            )
            setItemText(
                ItemContentListBinding.bind(viewParagraph4.root),
                R.string.meceiot_title_4,
                R.string.meceiot_content_4
            )
        }
    }

    private fun setItemText(itemBinding: ItemContentListBinding, titleId: Int, descriptionId: Int) {
        itemBinding.titleInfo.setText(titleId)
        itemBinding.textInfo.setText(descriptionId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}