package com.mymind.ui.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mymind.core.Database
import com.mymind.core.StatisticsModel
import com.mymind.core.base.BaseFragment
import com.mymind.databinding.FragmentStatisticsBinding

class StatisticsFragment : BaseFragment<FragmentStatisticsBinding>() {
    override fun setupBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentStatisticsBinding.inflate(layoutInflater, container, false)

    override fun FragmentStatisticsBinding.initializeLayout() {
        val db = Database()
        val statistic = StatisticsModel(db)
        statistic.calculateMoodStatistic()
        DonutRealisation(donutView, statistic).initDonut()
    }
}