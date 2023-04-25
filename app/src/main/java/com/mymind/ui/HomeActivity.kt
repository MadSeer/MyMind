package com.mymind.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mymind.core.BaseActivity
import com.mymind.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    override fun setupBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = ActivityHomeBinding.inflate(inflater, container, false)

    override fun ActivityHomeBinding.initializeLayout() {}
}