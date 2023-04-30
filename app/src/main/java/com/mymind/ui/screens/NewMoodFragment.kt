package com.mymind.ui.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.mymind.core.BaseFragment
import com.mymind.databinding.FragmentNewMoodLayoutBinding

class NewMoodFragment : BaseFragment<FragmentNewMoodLayoutBinding>() {
    override fun setupBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentNewMoodLayoutBinding.inflate(layoutInflater, container, false)

    override fun FragmentNewMoodLayoutBinding.initializeLayout() {
        CarouselMoodRealisation(viewPager)
        TabLayoutMediator(tabLayout, viewPager) { tab, position -> }.attach()
        viewPager.setCurrentItem(2, false)
        writeMood.setOnClickListener {
        }
    }
}