package com.mymind.core

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.RealmUUID
import io.realm.kotlin.types.annotations.PrimaryKey
import kotlinx.serialization.Serializable

class UserMoodModel() : RealmObject {
    @PrimaryKey
    var id: RealmUUID = RealmUUID.random()
    var commentary: String = ""
    var minute: String = "0"
    var hour: String = "0"
    var mood: Int = 0
    var date: String = ""
}