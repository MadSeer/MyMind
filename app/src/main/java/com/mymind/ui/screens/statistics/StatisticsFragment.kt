package com.mymind.ui.screens.statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.mymind.core.UserMoodModel
import com.mymind.core.base.BaseFragment
import com.mymind.databinding.FragmentStatisticsBinding
import io.realm.kotlin.query.RealmResults
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class StatisticsFragment : BaseFragment<FragmentStatisticsBinding>() {

    private val viewModel: StatisticsViewModel by viewModel()

    override fun setupBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentStatisticsBinding.inflate(layoutInflater, container, false)

    override fun FragmentStatisticsBinding.initializeLayout() {
        viewModel.mooddbLiveData.observe(viewLifecycleOwner, ::handleList)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getList()
    }

    private fun handleList(userMoodModels: RealmResults<UserMoodModel>?) {
        viewModel.setStatistic(binding.donutView)
    }
}