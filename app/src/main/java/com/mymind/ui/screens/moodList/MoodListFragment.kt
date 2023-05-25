package com.mymind.ui.screens.moodList

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.core.view.isGone
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.mymind.R
import com.mymind.core.UserMoodModel
import com.mymind.core.base.BaseFragment
import com.mymind.databinding.FragmentMoodListBinding
import eightbitlab.com.blurview.RenderScriptBlur
import io.realm.kotlin.query.RealmResults
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoodListFragment : BaseFragment<FragmentMoodListBinding>() {

    private val viewModel: MoodListViewModel by viewModel()
    override fun setupBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMoodListBinding.inflate(layoutInflater, container, false)

    override fun FragmentMoodListBinding.initializeLayout() {
        viewModel.mooddbLiveData.observe(viewLifecycleOwner, ::handleList)

        val radius = 5f
        val decorView = requireActivity().window.decorView
        val rootView = recyclerViewMoodList
        val windowBackground = decorView.background

        blurView.setupWith(
            rootView,
            RenderScriptBlur(requireContext())
        ) // or RenderEffectBlur
            .setFrameClearDrawable(windowBackground) // Optional
            .setBlurRadius(radius)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getList()
    }

    private fun handleList(
        userMoodModels: RealmResults<UserMoodModel>?
    ) {
        val adapter = userMoodModels?.let {
            MoodListFragmentRecyclerViewAdapter(
                it
            ) {
                binding.blurView.isGone = false
                val mainViewPager = activity
                    ?.findViewById<ViewPager2>(R.id.view_pager_activity_main)
                val mainTabLayout = activity
                    ?.findViewById<TabLayout>(R.id.tabLayout)

                mainViewPager!!.isUserInputEnabled = false
                mainTabLayout!!.isGone = true
                // binding.recyclerViewMoodList.isNestedScrollingEnabled = false

                val demoData = arrayListOf( // В базовой карусели есть этот же кал
                    // ,нужно спросить у Вани как это лучше
                    // сделать: классом чи как
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

                binding
                    .blurViewConstraintLayout
                    .setOnTouchListener { v, event ->
                        when (event.action) {
                            MotionEvent.ACTION_UP -> {
                                val x = event.x
                                val y = event.y
                                var rect = Rect()
                                binding.selectedTextMood.getGlobalVisibleRect(rect)
                                /*binding.selectedPictureMood.getGlobalVisibleRect(rect)
                                binding.textViewSelectedTime.getGlobalVisibleRect(rect)
                                binding.textViewSelectedDate.getGlobalVisibleRect(rect)*/
                                if (!rect.contains(x.toInt(), y.toInt())) {
                                    binding.blurView.isGone = true
                                    mainViewPager!!.isUserInputEnabled = true
                                    mainTabLayout!!.isGone = false
                                }
                            }
                        }
                        false
                    }
            }
        }
        binding.recyclerViewMoodList.adapter = adapter
        binding.recyclerViewMoodList.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun returnToList() {
    }
}
