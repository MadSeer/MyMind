package com.mymind.core

import SerializedUserMoodModel
import io.realm.kotlin.types.RealmUUID
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Serialisator() {

    /*fun getModel(model: UserMoodModel):SerializedUserMoodModel {
        this.id = model.id
        this.commentary = model.commentary
        this.minute = model.minute
        this.hour = model.hour
        this.mood = model.mood
        this.date = model.date
    }*/

    fun serialiseUserMoodModel(model: UserMoodModel): String {
        var newModel = SerializedUserMoodModel()
        newModel.getModel(model)
        val serialisedModel = Json.encodeToString(newModel)
        return serialisedModel
    }

    fun deserialiseUserMoodModel(serialisedModel: String): UserMoodModel {
        val model = Json.decodeFromString<SerializedUserMoodModel>(serialisedModel)
        val encodedModel = UserMoodModel()
        encodedModel.mood = model.mood
        encodedModel.id = RealmUUID.Companion.from(model.id)
        encodedModel.date = model.date
        encodedModel.commentary = model.commentary
        encodedModel.hour = model.hour
        encodedModel.minute = model.minute
        return encodedModel
    }
}
