package com.mymind.ui.screens.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mymind.core.base.BaseActivity
import com.mymind.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    override fun setupBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = ActivityHomeBinding.inflate(inflater, container, false)

    override fun ActivityHomeBinding.initializeLayout() {
        viewPager.adapter = HomeCarouselRealisation(supportFragmentManager, lifecycle)
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"))
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"))
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"))
    }
}