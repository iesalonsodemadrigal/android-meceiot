package edu.iesam.meceiot.features.lorawan.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_meceiot.databinding.FragmentAboutLorawanBinding
import edu.iesam.meceiot.features.lorawan.domain.LoraWanInfo
import edu.iesam.meceiot.features.lorawan.presentation.adapter.LoraWanAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoraWanAboutFragment : Fragment() {
    //private lateinit var loraWanFactory: LoraWanFactory
    //private lateinit var loraWanViewModel: LoraWanViewModel
    val loraWanViewModel: LoraWanViewModel by viewModel()

    private var _binding: FragmentAboutLorawanBinding? = null
    private val binding get() = _binding!!

    private val loraWanAdapter = LoraWanAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutLorawanBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //loraWanFactory = LoraWanFactory(requireContext())
        //loraWanViewModel = loraWanFactory.provideGetInfoLoraWan()
        setupObserver()
        loraWanViewModel.viewCreated()
    }

    private fun setupObserver() {
        val loraWanObserver = Observer<LoraWanViewModel.UiState> { uiState ->
            uiState.infoLoraWan?.let {
                bindData(it)
            }
            uiState.errorApp?.let {
                //print error
            } ?: run {
                // hide error
            }
            if (uiState.isLoading) {
                //show loading
            } else {
                //hide loading
            }
        }
        loraWanViewModel.uiState.observe(viewLifecycleOwner, loraWanObserver)
    }

    private fun setupView() {
        binding.apply {
            recyclerViewLorawanInfo.layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.VERTICAL, false
            )
            recyclerViewLorawanInfo.adapter = loraWanAdapter
        }
    }


    private fun bindData(loraWanInfo: List<LoraWanInfo>) {
        loraWanAdapter.submitList(loraWanInfo.sortedBy { it.id.toInt() })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}