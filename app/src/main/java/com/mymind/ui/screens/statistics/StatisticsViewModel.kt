package com.mymind.ui.screens.statistics

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.futured.donut.DonutProgressView
import com.mymind.core.Database
import com.mymind.core.UserMoodModel
import io.realm.kotlin.query.RealmResults
import kotlinx.coroutines.coroutineScope

class StatisticsViewModel(private val database: Database) : ViewModel() {

    val mooddbLiveData: MutableLiveData<RealmResults<UserMoodModel>> = MutableLiveData()

    fun setStatistic(donutView: DonutProgressView) {
        val statistic = StatisticsModel()
        statistic.calculateMoodStatistic(mooddbLiveData.value)
        DonutRealisation(donutView, statistic).initDonut()
    }

    fun getList() {
        val list = database.getData()
        mooddbLiveData.postValue(list)
    }
}