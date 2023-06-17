import com.mymind.core.UserMoodModel
import io.realm.kotlin.types.RealmUUID
import kotlinx.serialization.Serializable

@Serializable
open class SerializedUserMoodModel {

    var id = ""
    var commentary: String = ""
    var minute: String = "0"
    var hour: String = "0"
    var mood: Int = 0
    var date: String = ""

    open fun getModel(model: UserMoodModel) {
        this.id = model.id.toString()
        this.commentary = model.commentary
        this.minute = model.minute
        this.hour = model.hour
        this.mood = model.mood
        this.date = model.date
    }
}