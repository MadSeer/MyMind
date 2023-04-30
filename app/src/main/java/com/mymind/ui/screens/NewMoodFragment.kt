package com.mymind.ui.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayoutMediator
import com.mymind.core.BaseFragment
import com.mymind.core.Database
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
                var db = Database()
                db.setMood(viewPager.currentItem)
                db.setCommentary(moodCommentaryPlainText.text.toString())
                db.setTime(timePicker1.minute, timePicker1.hour)
                lifecycleScope.launch(Dispatchers.Main) {
                    requireActivity().finish()
                }
            }
        }
    }
}