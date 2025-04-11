package edu.iesam.meceiot.features.iot.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import edu.iesam.meceiot.databinding.FragmentIotDetailBinding

class IotDetailFragment : Fragment() {

    private val args: IotDetailFragmentArgs by navArgs()

    private var _binding: FragmentIotDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIotDetailBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    private fun setupView() {
        binding.apply {
            viewToolbar.toolbarIot.title = getString(args.titleResId)
            viewToolbar.toolbarIot.setNavigationOnClickListener {
                findNavController().navigateUp()
            }
            LayoutInflater.from(requireContext()).inflate(
                args.layoutResId,
                layoutIot,
                true
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 