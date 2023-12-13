package deu.ac.kr.csw.chatting.chat

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.stfalcon.chatkit.commons.ImageLoader
import com.stfalcon.chatkit.dialogs.DialogsListAdapter
import deu.ac.kr.csw.chatting.R
import deu.ac.kr.csw.chatting.databinding.ActivityDialogBinding
import deu.ac.kr.csw.chatting.friends.FriendsListActivity
import deu.ac.kr.csw.chatting.more.MoreActivity
import deu.ac.kr.csw.chatting.user.model.Dialog


class DialogActivity : AppCompatActivity(), DialogsListAdapter.OnDialogClickListener<Dialog?>,
    DialogsListAdapter.OnDialogLongClickListener<Dialog?> {

    private lateinit var binding: ActivityDialogBinding

    private var imageLoader: ImageLoader? = null
    private var dialogsAdapter: DialogsListAdapter<Dialog>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setBottomNavigation()
        imageLoader =
            ImageLoader { imageView: ImageView?, url: String?, payload: Any? ->
                Picasso.get().load(url).into(imageView)
            }
    }

    override fun onDialogClick(dialog: Dialog?) {
        startActivity(Intent(this, ChatActivity::class.java))
    }

    private fun initAdapter() {
        dialogsAdapter = DialogsListAdapter(imageLoader)
        //dialogsAdapter?.setItems(DialogsFixtures.getDialogs())
        dialogsAdapter?.setOnDialogClickListener(this)
        dialogsAdapter?.setOnDialogLongClickListener(
            this
        )
        binding.dialogsList.setAdapter(dialogsAdapter)
    }

    override fun onDialogLongClick(dialog: Dialog?) {
        Toast.makeText(this, dialog?.dialogName, Toast.LENGTH_SHORT).show()
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