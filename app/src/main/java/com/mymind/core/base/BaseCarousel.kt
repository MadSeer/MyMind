package com.mymind.core.base

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout

abstract class BaseCarousel(private val viewPager2: ViewPager2, private val tabLayout: TabLayout) {


    abstract fun viewPagerSettings(viewPager2: ViewPager2)

    abstract fun tabLayoutSettings(viewPager2: ViewPager2, tabLayout: TabLayout)


    fun initialaseCarousel() {
        viewPagerSettings(viewPager2)
        tabLayoutSettings(viewPager2, tabLayout)
    }
}