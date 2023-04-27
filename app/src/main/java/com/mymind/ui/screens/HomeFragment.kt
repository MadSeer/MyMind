package com.mymind.ui.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mymind.R
import com.mymind.core.BaseFragment
import com.mymind.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun setupBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(layoutInflater, container, false)

    override fun FragmentHomeBinding.initializeLayout() {

        button.setOnClickListener {
            findNavController().navigate(R.id.newMoodFragment)
        }
    }
}