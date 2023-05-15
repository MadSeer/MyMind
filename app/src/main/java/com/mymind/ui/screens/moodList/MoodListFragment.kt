package com.mymind.ui.screens.moodList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mymind.core.UserMoodModel
import com.mymind.core.base.BaseFragment
import com.mymind.databinding.FragmentMoodListBinding
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
    }

    override fun onResume() {
        super.onResume()
        viewModel.getList()
    }

    private fun handleList(userMoodModels: RealmResults<UserMoodModel>?) {
        val adapter = userMoodModels?.let { MoodListFragmentRecyclerViewAdapter(it) }
        binding.recyclerViewMoodList.adapter = adapter
        binding.recyclerViewMoodList.layoutManager = LinearLayoutManager(requireContext())
    }
}