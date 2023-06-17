package com.mymind.core

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.types.RealmUUID
import java.text.SimpleDateFormat
import java.util.Calendar
import kotlinx.coroutines.runBlocking

class Database {

    private val config = RealmConfiguration.Builder(
        schema = setOf(UserMoodModel::class)
    ).schemaVersion(3).build()

    private val realm: Realm = Realm.open(config)

    fun setMoodData(minute: String, hour: String, mood: Int, commentary: String) {
        realm.writeBlocking {
            copyToRealm(
                UserMoodModel().apply {
                    if (minute.length < 2) {
                        this.minute = "0" + minute
                    } else {
                        this.minute = minute
                    }

                    if (hour.length < 2) {
                        this.hour = "0" + hour
                    } else {
                        this.hour = hour
                    }

                    val dt = SimpleDateFormat("dd.MM.yyyy")
                    this.date = dt.format(Calendar.getInstance().time)

                    this.mood = mood
                    this.commentary = commentary
                }
            )
        }
    }

    fun changeMood(model: UserMoodModel) = runBlocking {
        // deleteMood(model.id)
        realm.writeBlocking {
            copyToRealm(
                model.apply {
                    if (minute.length < 2) {
                        this.minute = "0" + model.minute
                    } else {
                        this.minute = model.minute
                    }
                    if (hour.length < 2) {
                        this.hour = "0" + model.hour
                    } else {
                        this.hour = model.hour
                    }
                    this.id = model.id
                    this.date = model.date
                    this.mood = model.mood
                    this.commentary = model.commentary
                },
                updatePolicy = UpdatePolicy.ALL
            )
        }
    }

    fun deleteMood(uuid: RealmUUID) = runBlocking {
        realm.write {
            val user = this.query<UserMoodModel>("id == $0", uuid)
                .find()
            delete(user)
        }
    }

    fun getData(): RealmResults<UserMoodModel> {
        return realm.query<UserMoodModel>().find()
    }
}