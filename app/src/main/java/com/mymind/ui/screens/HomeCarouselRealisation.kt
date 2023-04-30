package com.mymind.ui.screens

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeCarouselRealisation(
    manager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(manager, lifecycle) {

    val demoData = arrayListOf(
        HomeFragment(),
        MoodListFragment()
    )

    override fun getItemCount(): Int {
        return demoData.size
    }

    override fun createFragment(position: Int): Fragment {
        return demoData[position]
    }
}