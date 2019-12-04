package ai.akun.nukasdk.chatbot.data

import ai.akun.nukasdk.chatbot.data.chatmessage.entities.*
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

    @TypeConverter
    fun toProducts(value: String?): List<ProductEntity?>? {
        val listType = object : TypeToken<List<ProductEntity?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toProductsJson(list: List<ProductEntity?>?): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toArticles(value: String?): List<ArticleEntity?>? {
        val listType = object : TypeToken<List<ArticleEntity?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toArticlesJson(list: List<ArticleEntity?>?): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toRankings(value: String?): List<RankingEntity?>? {
        val listType = object : TypeToken<List<RankingEntity?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toRankingsJson(list: List<RankingEntity?>?): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toBookings(value: String?): List<BookingEntity?>? {
        val listType = object : TypeToken<List<BookingEntity?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toBookingsJson(list: List<BookingEntity?>?): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toScorers(value: String?): List<ScorerEntity?>? {
        val listType = object : TypeToken<List<ScorerEntity?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toScorersJson(list: List<ScorerEntity?>?): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toSubstitutions(value: String?): List<SubstitutionEntity?>? {
        val listType = object : TypeToken<List<SubstitutionEntity?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toSubstitutionsJson(list: List<SubstitutionEntity?>?): String? {
        return Gson().toJson(list)
    }
}