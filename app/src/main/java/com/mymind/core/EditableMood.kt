package com.mymind.core

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.RealmUUID
import kotlinx.coroutines.runBlocking

class EditableMood {

    private val configEditableMood = RealmConfiguration.Builder(
        schema = setOf(EditableMoodModel()::class)
    ).schemaVersion(2).name("editableMood").build()

    private val realmEditableMood: Realm = Realm.open(configEditableMood)

    fun setEditableMood(model: UserMoodModel) = runBlocking {
        deleteEditableMood()
        val editableModel = EditableMoodModel()
        editableModel.setData(model)
        realmEditableMood.write {
            copyToRealm(editableModel)
            editableModel
        }
    }

    fun getEditableMood(): UserMoodModel = runBlocking {
        var mood = UserMoodModel()
        realmEditableMood.write {
            val current = this.query<EditableMoodModel>().find().first()
            mood = EditableMoodModel().getData(current)
        }
        return@runBlocking mood
    }

    fun deleteEditableMood() = runBlocking {
        realmEditableMood.write {
            val privious: RealmResults<EditableMoodModel> = this.query<EditableMoodModel>().find()
            delete(privious)
        }
    }

    inner class EditableMoodModel() : RealmObject {

        var id: RealmUUID = RealmUUID.random()
        var commentary: String = ""
        var minute: String = "0"
        var hour: String = "0"
        var mood: Int = 0
        var date: String = ""

        fun setData(model: UserMoodModel) {
            this@EditableMoodModel.id = model.id
            this@EditableMoodModel.commentary = model.commentary
            this@EditableMoodModel.minute = model.minute
            this@EditableMoodModel.hour = model.hour
            this@EditableMoodModel.mood = model.mood
            this@EditableMoodModel.date = model.date
        }

        fun getData(current: EditableMoodModel): UserMoodModel {
            val mood = UserMoodModel().apply {
                id = current.id
                commentary = current.commentary
                minute = current.hour
                hour = current.hour
                mood = current.mood
                date = current.date
            }
            return mood
        }
    }
}