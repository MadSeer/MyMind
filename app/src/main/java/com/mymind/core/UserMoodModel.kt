package com.mymind.core

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Index
import io.realm.kotlin.types.annotations.PrimaryKey
import java.text.SimpleDateFormat
import java.util.UUID
import org.mongodb.kbson.ObjectId

class UserMoodModel() : RealmObject {
    @PrimaryKey
    var id: UUID = UUID.randomUUID()
    var commentary: String = ""
    var minute: String = "0"
    var hour: String = "0"
    var mood: Int = 0
    var date: String = ""
}