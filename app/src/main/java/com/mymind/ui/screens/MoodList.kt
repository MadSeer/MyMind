package com.mymind.ui.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mymind.core.BaseFragment
import com.mymind.databinding.FragmentMoodListBinding

class MoodList : BaseFragment<FragmentMoodListBinding>() {
    override fun setupBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMoodListBinding.inflate(layoutInflater, container, false)

    override fun FragmentMoodListBinding.initializeLayout() {
    }
}