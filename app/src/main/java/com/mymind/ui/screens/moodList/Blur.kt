package com.mymind.ui.screens.moodList

import androidx.core.view.isGone
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.mymind.R
import com.mymind.core.UserMoodModel
import com.mymind.databinding.FragmentMoodListBinding

class Blur(
    private val binding: FragmentMoodListBinding,
    private val activity: FragmentActivity,
    private val it: UserMoodModel,
    private val viewModel: MoodListViewModel,
    var callback: (String) -> Unit
) : ViewModel() {

    fun recyclerViewItemActions() {
        binding.blurView.isGone = false
        val mainViewPager = activity
            ?.findViewById<ViewPager2>(R.id.view_pager_activity_main)
        val mainTabLayout = activity
            ?.findViewById<TabLayout>(R.id.tabLayout)
        mainViewPager!!.isUserInputEnabled = false
        mainTabLayout!!.isGone = true

        val demoData = arrayListOf(
            R.drawable.cat_mood1,
            R.drawable.cat_mood2,
            R.drawable.cat_mood3,
            R.drawable.cat_mood4,
            R.drawable.cat_mood5
        )

        when (it.mood) {
            0 -> binding.selectedPictureMood.setImageResource(demoData[0])
            1 -> binding.selectedPictureMood.setImageResource(demoData[1])
            2 -> binding.selectedPictureMood.setImageResource(demoData[2])
            3 -> binding.selectedPictureMood.setImageResource(demoData[3])
            4 -> binding.selectedPictureMood.setImageResource(demoData[4])
        }
        binding.selectedTextMood.text = it.commentary
        binding.textViewSelectedDate.text = it.date
        binding.textViewSelectedTime.text = """
                    ${it.hour}:${it.minute}
        """.trimIndent()
        val model = it
        back(mainViewPager!!, mainTabLayout!!)
        delete(mainViewPager!!, mainTabLayout!!, model)
        edit(mainViewPager!!, mainTabLayout!!)
    }

    private fun delete(
        mainViewPager: ViewPager2,
        mainTabLayout: TabLayout,
        model: UserMoodModel
    ) {
        binding.delete.setOnClickListener {
            callback.invoke("delete")
            viewModel.delete(model.id)
            binding.blurView.isGone = true
            mainViewPager!!.isUserInputEnabled = true
            mainTabLayout!!.isGone = false
        }
    }

    private fun back(
        mainViewPager: ViewPager2,
        mainTabLayout: TabLayout
    ) {
        binding.back.setOnClickListener {
            binding.blurView.isGone = true
            mainViewPager!!.isUserInputEnabled = true
            mainTabLayout!!.isGone = false
        }
    }

    private fun edit(
        mainViewPager: ViewPager2,
        mainTabLayout: TabLayout
    ) {
        binding.edit.setOnClickListener {
            callback.invoke("edit")
            binding.blurView.isGone = true
            mainViewPager!!.isUserInputEnabled = true
            mainTabLayout!!.isGone = false
        }
    }
}