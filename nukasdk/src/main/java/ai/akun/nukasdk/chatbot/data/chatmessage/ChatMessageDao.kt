package ai.akun.nukasdk.chatbot.data.chatmessage

import ai.akun.nukasdk.chatbot.data.chatmessage.entities.ChatMessageEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ChatMessageDao {

    @Query("SELECT * FROM chat_message")
    fun getAll(): Single<List<ChatMessageEntity>>

    @Insert
    fun insert(vararg chatMessage: ChatMessageEntity): Completable
}