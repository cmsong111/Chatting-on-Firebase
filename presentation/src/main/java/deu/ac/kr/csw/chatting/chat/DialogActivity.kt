package deu.ac.kr.csw.chatting.chat

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import deu.ac.kr.csw.chatting.R
import deu.ac.kr.csw.chatting.databinding.ActivityDialogBinding
import deu.ac.kr.csw.chatting.friends.FriendsListActivity
import deu.ac.kr.csw.chatting.more.MoreActivity


class DialogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDialogBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setBottomNavigation()
    }


    private fun setBottomNavigation() {
        binding.bottomNavigationBar.selectedItemId = R.id.navigation_chats
        binding.bottomNavigationBar.setOnNavigationItemSelectedListener { item ->
            if (item.itemId === R.id.navigation_friends) {
                val intent = Intent(this, FriendsListActivity::class.java)
                startActivity(intent)
                finish()
            } else if (item.itemId === R.id.navigation_settings) {
                val intent3 = Intent(this, MoreActivity::class.java)
                startActivity(intent3)
                finish()
            }
            true
        }
    }

}