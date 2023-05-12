package com.mymind.ui.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayoutMediator
import com.mymind.R
import com.mymind.core.Database
import com.mymind.core.base.BaseFragment
import com.mymind.databinding.ActivityHomeBinding
import com.mymind.databinding.FragmentNewMoodLayoutBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewMoodFragment : BaseFragment<FragmentNewMoodLayoutBinding>() {
    override fun setupBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentNewMoodLayoutBinding.inflate(layoutInflater, container, false)

    override fun FragmentNewMoodLayoutBinding.initializeLayout() {
        timePicker1.setIs24HourView(true)
        CarouselMoodRealisation(viewPager)
        TabLayoutMediator(tabLayout, viewPager) { tab, position -> }.attach()
        viewPager.setCurrentItem(2, false)
        writeMood.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val db = Database()
                db.setMoodData(
                    timePicker1.minute.toString(),
                    timePicker1.hour.toString(),
                    viewPager.currentItem,
                    moodCommentaryPlainText.text.toString()
                )
                lifecycleScope.launch(Dispatchers.Main) {
                    requireActivity().finish()
                }
            }
        }
    }
}