package com.mymind.ui.screens.newMood

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayoutMediator
import com.mymind.core.Database
import com.mymind.core.base.BaseFragment
import com.mymind.databinding.FragmentNewMoodLayoutBinding
import com.mymind.ui.screens.home.CarouselMoodRealisation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewMoodFragment : BaseFragment<FragmentNewMoodLayoutBinding>() {
    override fun setupBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentNewMoodLayoutBinding.inflate(layoutInflater, container, false)

    override fun FragmentNewMoodLayoutBinding.initializeLayout() {
        timePicker1.setIs24HourView(true)
        CarouselMoodRealisation(viewPagerActivityMain)
        TabLayoutMediator(tabLayout, viewPagerActivityMain) { tab, position -> }.attach()
        viewPagerActivityMain.setCurrentItem(2, false)
        writeMood.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val db = Database()
                db.setMoodData(
                    timePicker1.minute.toString(),
                    timePicker1.hour.toString(),
                    viewPagerActivityMain.currentItem,
                    moodCommentaryPlainText.text.toString()
                )
                lifecycleScope.launch(Dispatchers.Main) {
                    requireActivity().finish()
                }
            }
        }
    }
}