package deu.ac.kr.csw.chatting.chat

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import com.stfalcon.chatkit.commons.ImageLoader
import com.stfalcon.chatkit.dialogs.DialogsListAdapter
import dagger.hilt.android.AndroidEntryPoint
import deu.ac.kr.csw.chatting.R
import deu.ac.kr.csw.chatting.chat.model.Dialog
import deu.ac.kr.csw.chatting.databinding.ActivityDialogBinding
import deu.ac.kr.csw.chatting.friends.FriendsListActivity
import deu.ac.kr.csw.chatting.more.MoreActivity
import deu.ac.kr.csw.chatting.widget.LoadingDialog
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class DialogActivity : AppCompatActivity(), DialogsListAdapter.OnDialogClickListener<Dialog?>,
    DialogsListAdapter.OnDialogLongClickListener<Dialog?> {

    private lateinit var binding: ActivityDialogBinding

    private var imageLoader: ImageLoader? = null
    private var dialogsAdapter: DialogsListAdapter<Dialog>? = null

    @Inject
    lateinit var dialogRepository: DialogRepository

   lateinit var loadingDialog: LoadingDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setBottomNavigation()
        imageLoader =
            ImageLoader { imageView: ImageView?, url: String?, payload: Any? ->
                Picasso.get().load(url).into(imageView)
            }
        initAdapter()
        loadingDialog = LoadingDialog(this)
        loadingDialog.show()

    }

    override fun onDialogClick(dialog: Dialog?) {
        val intent : Intent = Intent(this, ChatActivity::class.java)
        if (dialog != null) {
            intent.putExtra("dialogId", dialog.id)
        }
        startActivity(intent)
    }

    private fun initAdapter() {
        dialogsAdapter = DialogsListAdapter(imageLoader)
        dialogsAdapter?.setOnDialogClickListener(this)
        dialogsAdapter?.setOnDialogLongClickListener(this)
        binding.dialogsList.setAdapter(dialogsAdapter)

        lifecycleScope.launch {
            Log.d("DialogActivity", "Dialog List Loading with '${FirebaseAuth.getInstance().uid!!}'")
            dialogRepository.getDialogList(FirebaseAuth.getInstance().uid!!).let {
                dialogsAdapter?.setItems(it)
                Log.d("DialogActivity", "Dialog List Loaded: $it")
                loadingDialog.dismiss()
            }
        }
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