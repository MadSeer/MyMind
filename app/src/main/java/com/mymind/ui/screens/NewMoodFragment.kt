package com.mymind.ui.screens

import CarouselAdapter
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.google.android.material.tabs.TabLayoutMediator
import com.mymind.R
import com.mymind.core.BaseFragment
import com.mymind.databinding.NewMoodLayoutBinding

class NewMoodFragment : BaseFragment<NewMoodLayoutBinding>() {
    override fun setupBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ) = NewMoodLayoutBinding.inflate(layoutInflater, container, false)

    override fun NewMoodLayoutBinding.initializeLayout() {
        CarouselMoodRealisation(viewPager, tabLayout)
    }
}