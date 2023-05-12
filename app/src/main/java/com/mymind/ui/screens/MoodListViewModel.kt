package com.mymind.ui.screens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mymind.core.Database
import com.mymind.core.UserMoodModel
import io.realm.kotlin.query.RealmResults

class MoodListViewModel : ViewModel() {

    val mooddbLiveData: MutableLiveData<RealmResults<UserMoodModel>> = MutableLiveData()

    fun getList() {
        val list = Database().getData()
        mooddbLiveData.postValue(list)
    }
}