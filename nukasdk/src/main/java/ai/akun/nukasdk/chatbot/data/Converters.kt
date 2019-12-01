package ai.akun.nukasdk.chatbot.data

import ai.akun.nukasdk.chatbot.data.chatmessage.entities.CardEntity
import ai.akun.nukasdk.chatbot.data.chatmessage.entities.MatchEntity
import ai.akun.nukasdk.chatbot.data.chatmessage.entities.PlayerEntity
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
    fun toCardsJson(list: List<CardEntity?>?): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toMatches(value: String?): List<MatchEntity?>? {
        val listType = object : TypeToken<List<MatchEntity?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toMatchesJson(list: List<MatchEntity?>?): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toPlayers(value: String?): List<PlayerEntity?>? {
        val listType = object : TypeToken<List<PlayerEntity?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toPlayersJson(list: List<PlayerEntity?>?): String? {
        return Gson().toJson(list)
    }
}