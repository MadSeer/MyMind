package com.mymind.ui.screens

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mymind.core.base.BaseFragment
import com.mymind.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun setupBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(layoutInflater, container, false)

    override fun FragmentHomeBinding.initializeLayout() {

        button.setOnClickListener {
            val intent = Intent(activity, NewMoodActivity::class.java)
            activity?.startActivity(intent)
            // findNavController().navigate(R.id.newMoodFragment)
        }
    }
}