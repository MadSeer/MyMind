package com.mymind.ui.screens

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.mymind.R
import com.mymind.core.base.BaseCarousel

class HomeCarouselRealisation(
    manager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(manager, lifecycle) {

    val demoData = arrayListOf(
        HomeFragment(),
        MoodList()
    )

    override fun getItemCount(): Int {
        return demoData.size
    }

    override fun createFragment(position: Int): Fragment {
        return demoData[position]
    }
}