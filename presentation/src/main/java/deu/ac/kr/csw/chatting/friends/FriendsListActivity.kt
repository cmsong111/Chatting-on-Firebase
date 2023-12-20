package deu.ac.kr.csw.chatting.friends

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import deu.ac.kr.csw.chatting.R
import deu.ac.kr.csw.chatting.chat.DialogActivity
import deu.ac.kr.csw.chatting.chat.DialogRepository
import deu.ac.kr.csw.chatting.chat.model.Dialog
import deu.ac.kr.csw.chatting.databinding.ActivityFriendsListBinding
import deu.ac.kr.csw.chatting.more.MoreActivity
import deu.ac.kr.csw.chatting.user.model.User
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FriendsListActivity : AppCompatActivity() {
    lateinit var binding: ActivityFriendsListBinding
    lateinit var viewModel: FriendListViewModel

    @Inject
    lateinit var dialogRepository: DialogRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFriendsListBinding.inflate(getLayoutInflater())
        viewModel = ViewModelProvider(this).get(
            FriendListViewModel::class.java
        )
        binding.setLifecycleOwner(this)
        binding.setViewModel(viewModel)
        binding.friendsListView.setAdapter(viewModel!!.friendListAdapter)


        supportActionBar!!.title = "친구"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        setContentView(binding.getRoot())
        setBottomNavigation()

        binding.friendsListView.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this, "친구를 클릭했습니다.", Toast.LENGTH_SHORT).show()
            //getItem(position);
            val user =
                viewModel!!.friendListAdapter.getItem(position) as User
            Log.d("FriendsListActivity", "user: $user")

            lifecycle.coroutineScope.launch {
                val dialog : Dialog = dialogRepository.getDialog(
                    myUid =FirebaseAuth.getInstance().uid!!,
                    yourUid =user.uid
                )
                Log.d("FriendsListActivity", "dialog: $dialog")
            }
        }

    }

    fun setBottomNavigation() {
        binding.bottomNavigationBar.setSelectedItemId(R.id.navigation_friends)
        binding.bottomNavigationBar.setOnNavigationItemSelectedListener { item ->
            if (item.getItemId() === R.id.navigation_chats) {
                val intent2 = Intent(this, DialogActivity::class.java)
                startActivity(intent2)
                finish()
            } else if (item.getItemId() === R.id.navigation_settings) {
                val intent3 = Intent(this, MoreActivity::class.java)
                startActivity(intent3)
                finish()
            }
            true
        }
    }

}