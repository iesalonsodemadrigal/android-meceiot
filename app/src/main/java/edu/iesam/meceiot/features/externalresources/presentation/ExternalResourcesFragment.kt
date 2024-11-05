package edu.iesam.meceiot.features.externalresources.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android_meceiot.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class ExternalResourcesFragment : BottomSheetDialogFragment(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_external_resources, container, false)
    }


}