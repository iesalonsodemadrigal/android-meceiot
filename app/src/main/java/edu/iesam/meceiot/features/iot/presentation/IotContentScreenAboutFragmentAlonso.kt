package edu.iesam.meceiot.features.iot.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import edu.iesam.meceiot.R
import edu.iesam.meceiot.databinding.FragmentIotContentAlonsoBinding
import edu.iesam.meceiot.databinding.ItemContentListBinding
import edu.iesam.meceiot.databinding.ItemContentListP2Binding


class IotContentScreenAboutFragmentAlonso : Fragment(R.layout.fragment_iot_content_alonso) {
    private var _binding: FragmentIotContentAlonsoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIotContentAlonsoBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    private fun setupView() {
        binding.apply {
            viewDetailedMeceiot.mainToolbarDetail.setNavigationOnClickListener {
                findNavController().navigateUp()
            }

            viewDetailedMeceiot.mainToolbarDetail.title = getString(R.string.title_item_alonso)

            setItemTextP2(
                ItemContentListP2Binding.bind(viewTextContainer1.root),
                R.string.alonso_title_1,
                R.string.alonso_content_1,
                R.string.alonso_content_1_1
            )
            setItemText(
                ItemContentListBinding.bind(viewTextContainer2.root),
                R.string.alonso_title_2,
                R.string.alonso_content_2
            )

        }
    }

    private fun setItemText(itemBinding: ItemContentListBinding, titleId: Int, descriptionId: Int) {
        itemBinding.titleInfo.setText(titleId)
        itemBinding.textInfo.setText(descriptionId)
    }

    private fun setItemTextP2(itemBinding: ItemContentListP2Binding, titleId: Int, descriptionId: Int, descriptionId2: Int) {
        itemBinding.titleInfo.setText(titleId)
        itemBinding.textInfoP1.setText(descriptionId)
        itemBinding.textInfoP2.setText(descriptionId2)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}