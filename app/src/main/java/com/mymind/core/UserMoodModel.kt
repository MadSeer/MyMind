package com.mymind.core

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Index
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class UserMoodModel() : RealmObject {
    @PrimaryKey
    var id: ObjectId = ObjectId.invoke()
    var commentary: String = ""
    var minute: Int = 0
    var hour: Int = 0
    var ampm: Boolean = true // true = AM, false = PM
    var mood: Int = 0
}