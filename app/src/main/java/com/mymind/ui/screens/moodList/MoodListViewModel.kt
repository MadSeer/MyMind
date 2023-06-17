package com.mymind.ui.screens.moodList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mymind.core.Database
import com.mymind.core.UserMoodModel
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.types.RealmUUID

class MoodListViewModel(
    private val database: Database
) : ViewModel() {

    val mooddbLiveData: MutableLiveData<RealmResults<UserMoodModel>> = MutableLiveData()

    fun getList() {
        val list = database.getData()
        mooddbLiveData.postValue(list)
    }

    fun delete(uuid: RealmUUID) {
        database.deleteMood(uuid)
        getList()
    }

    fun edit(model: UserMoodModel) {
    }
}