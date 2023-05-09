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

    fun setMoodData(minute: String, hour: String, mood: Int, commentary: String) {
        realm.writeBlocking {
            copyToRealm(
                UserMoodModel().apply {
                    if (minute.length <2) {
                        this.minute = "0" + minute
                    } else this.minute = minute

                    if (hour.length <2) {
                        this.hour = "0" + hour
                    } else this.hour = hour

                    val dt = SimpleDateFormat("dd.MM.yyyy")
                    this.date = dt.format(Calendar.getInstance().time)

                    this.mood = mood
                    this.commentary = commentary
                }
            )
        }
    }

    fun changeMood(uuid: UUID) {
        realm.writeBlocking {
            realm.query<UserMoodModel>("id == %0", uuid.toString()).first().apply {
            }
        }
    }

    fun getData(): RealmResults<UserMoodModel> {
        return realm.query<UserMoodModel>().find()
    }
}