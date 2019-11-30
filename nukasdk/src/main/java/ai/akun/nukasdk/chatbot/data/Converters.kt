package ai.akun.nukasdk.chatbot.data

import ai.akun.nukasdk.chatbot.data.chatmessage.entities.CardEntity
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun toCards(value: String?): List<CardEntity?>? {
        val listType = object : TypeToken<List<CardEntity?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toJson(list: List<CardEntity?>?): String? {
        return Gson().toJson(list)
    }

}