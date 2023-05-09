package com.mymind.ui.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mymind.core.base.BaseActivity
import com.mymind.databinding.ActivityNewMoodBinding

class NewMoodActivity : BaseActivity<ActivityNewMoodBinding>() {
    override fun setupBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = ActivityNewMoodBinding.inflate(inflater, container, false)

    override fun ActivityNewMoodBinding.initializeLayout() {
    }
}