package com.mymind.ui.screens.moodList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.mymind.core.UserMoodModel
import com.mymind.core.base.BaseFragment
import com.mymind.databinding.FragmentMoodListBinding
import eightbitlab.com.blurview.BlurView
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

    private fun handleList(userMoodModels: RealmResults<UserMoodModel>?) {
        val adapter = userMoodModels?.let {
            MoodListFragmentRecyclerViewAdapter(
                it
            ) {
                binding.blurView.isVisible = true
            }
        }
        binding.recyclerViewMoodList.adapter = adapter
        binding.recyclerViewMoodList.layoutManager = LinearLayoutManager(requireContext())
    }
}
