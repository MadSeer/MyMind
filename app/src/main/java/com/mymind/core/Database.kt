package com.mymind.core

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import java.text.SimpleDateFormat
import java.util.*

class Database {

    private val config = RealmConfiguration.Builder(
        schema = setOf(UserMoodModel::class)
    ).schemaVersion(2).build()

    private val realm: Realm = Realm.open(config)

    fun setTime(minute: Int, hour: Int) {
        realm.writeBlocking {
            copyToRealm(
                UserMoodModel().apply {
                    this.minute = minute
                    this.hour = hour
                    val dt = SimpleDateFormat("dd.MM.yyyy")
                    this.date = dt.format(Calendar.getInstance().time)
                }
            )
        }
    }

    fun setMood(mood: Int) {
        realm.writeBlocking {
            copyToRealm(
                UserMoodModel().apply {
                    this.mood = mood
                }
            )
        }
    }

    fun setCommentary(commentary: String) {
        realm.writeBlocking {
            copyToRealm(
                UserMoodModel().apply {
                    this.commentary = commentary
                }
            )
        }
    }

    fun getData(): RealmResults<UserMoodModel> {
        return realm.query<UserMoodModel>().find()
    }
}