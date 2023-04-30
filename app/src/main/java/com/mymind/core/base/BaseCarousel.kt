package com.mymind.core.base

import CarouselAdapter
import android.content.res.Resources
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mymind.R

open class BaseCarousel(private val viewPager2: ViewPager2) {

    open val demoData = arrayListOf(
        R.drawable.cat_mood1,
        R.drawable.cat_mood2,
        R.drawable.cat_mood3,
        R.drawable.cat_mood4,
        R.drawable.cat_mood5
    )

    open fun viewPagerSettings(viewPager2: ViewPager2) {
        viewPager2.apply {
            clipChildren = false
            clipToPadding = false
            offscreenPageLimit = 3
            (getChildAt(0) as RecyclerView).overScrollMode =
                RecyclerView.OVER_SCROLL_NEVER
        }

        viewPager2.adapter = CarouselAdapter(demoData)

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(
            MarginPageTransformer(
                (40 * Resources.getSystem().displayMetrics.density)
                    .toInt()
            )
        )
        viewPager2.setPageTransformer(compositePageTransformer)
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - Math.abs(position)
            page.scaleY = (0.80f + r * 0.20f)
        }
        viewPager2.setPageTransformer(compositePageTransformer)
    }

    /*open fun tabLayoutSettings(viewPager2: ViewPager2, tabLayout: TabLayout) {
        TabLayoutMediator(tabLayout, viewPager2) { tab, position -> }.attach()
        viewPager2.setCurrentItem(2, false)
    }*/

    /*fun initialaseCarousel() {
        viewPagerSettings(viewPager2)
        tabLayoutSettings(viewPager2, tabLayout)
    }*/
}