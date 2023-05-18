package com.mymind.ui.screens.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayoutMediator
import com.mymind.R
import com.mymind.core.base.BaseActivity
import com.mymind.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    override fun setupBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = ActivityHomeBinding.inflate(inflater, container, false)

    override fun ActivityHomeBinding.initializeLayout() {
        viewPager.adapter = HomeCarouselRealisation(supportFragmentManager, lifecycle)

        val icons = listOf<Int>(
            R.drawable.tab_menu_0,
            R.drawable.tab_menu_1,
            R.drawable.tab_menu_2
        )

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            val drawNum = "tab_menu_" + "$position"
            /*tab.text = "$position"*/
            tab.icon = ContextCompat.getDrawable(
                this@HomeActivity,
                icons[position]
            )
        }.attach()
        /*tabLayout.addTab(tabLayout.newTab().setText("Tab 1"))
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"))
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"))*/
    }
}