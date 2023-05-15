package com.mymind.ui.screens.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mymind.ui.screens.moodList.MoodListFragment
import com.mymind.ui.screens.statistics.StatisticsFragment

class HomeCarouselRealisation(
    manager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(manager, lifecycle) {

    val demoData = arrayListOf(
        HomeFragment(),
        MoodListFragment(),
        StatisticsFragment()
    )

    override fun getItemCount(): Int {
        return demoData.size
    }

    override fun createFragment(position: Int): Fragment {
        return demoData[position]
    }
}