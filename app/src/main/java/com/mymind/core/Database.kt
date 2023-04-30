package com.mymind.core

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults

class Database {

    private val config = RealmConfiguration.create(schema = setOf(UserMoodModel::class))
    private val realm: Realm = Realm.open(config)

    fun setTime(minute: Int, hour: Int, ampm: Boolean) {
        realm.writeBlocking {
            copyToRealm(
                UserMoodModel().apply {
                    this.minute = minute
                    this.hour = hour
                    this.ampm = ampm
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