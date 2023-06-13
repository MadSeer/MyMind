package com.mymind.ui.screens.moodList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
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
        userMoodModels: RealmResults<UserMoodModel>?,
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

                binding.back.setOnClickListener {
                    binding.blurView.isGone = true
                    mainViewPager!!.isUserInputEnabled = true
                    mainTabLayout!!.isGone = false
                }

                val model = it
                binding.delete.setOnClickListener {
                    binding.blurView.isGone = true
                    mainViewPager!!.isUserInputEnabled = true
                    mainTabLayout!!.isGone = false
                    viewModel.delete(model.id)
                }
            }
        }

        binding.recyclerViewMoodList.adapter = adapter
        binding.recyclerViewMoodList.layoutManager = LinearLayoutManager(requireContext())
    }

    /*fun restart(){
        var tr = FragmentActivity().supportFragmentManager
        tr.beginTransaction().detach()
    }*/
}
