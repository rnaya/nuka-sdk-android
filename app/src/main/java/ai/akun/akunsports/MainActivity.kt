package ai.akun.akunsports


import ai.akun.nukasdk.chatbot.presentation.main.ChatBotActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(Intent(this, ChatBotActivity::class.java))

        openChatBot.setOnClickListener {
            startActivity(Intent(this, ChatBotActivity::class.java))
        }
    }

}