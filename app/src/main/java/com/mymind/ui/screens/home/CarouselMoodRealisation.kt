package com.mymind.ui.screens.home

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.mymind.core.base.BaseCarousel

class CarouselMoodRealisation(viewPager2: ViewPager2) :
    BaseCarousel(viewPager2) {

    init {
        viewPagerSettings(viewPager2)
    }
}