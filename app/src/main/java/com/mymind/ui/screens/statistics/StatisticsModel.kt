package com.mymind.ui.screens.statistics

import com.mymind.core.Database
import com.mymind.core.UserMoodModel
import io.realm.kotlin.query.RealmResults

class StatisticsModel() {
    var veryBad: Int = 0
    var bad: Int = 0
    var mid: Int = 0
    var good: Int = 0
    var veryGood: Int = 0
    var sum: Int = 0

    fun calculateMoodStatistic(list: RealmResults<UserMoodModel>?) {
        list
            ?.toList()
            ?.forEach { model ->
                when (model.mood) {
                    0 -> this.veryBad++
                    1 -> this.bad++
                    2 -> this.mid++
                    3 -> this.good++
                    4 -> this.veryGood++
                }
                this.sum++
            }
    }
}