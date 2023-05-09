package com.mymind.ui.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listapp.MoodListFragmentRecyclerViewAdapter
import com.mymind.core.Database
import com.mymind.core.base.BaseFragment
import com.mymind.databinding.FragmentMoodListBinding

class MoodListFragment : BaseFragment<FragmentMoodListBinding>() {
    override fun setupBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMoodListBinding.inflate(layoutInflater, container, false)

    override fun FragmentMoodListBinding.initializeLayout() {
        val rvMood = recyclerViewMoodList
        val db = Database().getData() // !!!!!

        val adapter = MoodListFragmentRecyclerViewAdapter(db)
        rvMood.adapter = adapter
        rvMood.layoutManager = LinearLayoutManager(requireContext())
    }
}