package com.mymind.ui.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listapp.MoodListFragmentRecyclerViewAdapter
import com.mymind.core.Database
import com.mymind.core.UserMoodModel
import com.mymind.core.base.BaseFragment
import com.mymind.databinding.FragmentMoodListBinding
import io.realm.kotlin.query.RealmResults

class MoodListFragment : BaseFragment<FragmentMoodListBinding>() {

    val viewModel = MoodListViewModel()
    override fun setupBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMoodListBinding.inflate(layoutInflater, container, false)

    override fun FragmentMoodListBinding.initializeLayout() {
        // db = Database().getData() // !!!!!

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