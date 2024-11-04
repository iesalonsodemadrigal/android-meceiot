package edu.iesam.meceiot.features.externalresources.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android_meceiot.R


class ExternalResourcesFragment : Fragment(R.layout.fragment_external_resources) {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_external_resources, container, false)
    }


}