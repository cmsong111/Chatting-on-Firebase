package deu.ac.kr.csw.chatting.chat

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.ktx.messaging
import com.squareup.picasso.Picasso
import com.stfalcon.chatkit.commons.ImageLoader
import com.stfalcon.chatkit.messages.MessageInput
import com.stfalcon.chatkit.messages.MessagesListAdapter
import dagger.hilt.android.AndroidEntryPoint
import deu.ac.kr.csw.chatting.R
import deu.ac.kr.csw.chatting.chat.model.Dialog
import deu.ac.kr.csw.chatting.chat.model.Message
import deu.ac.kr.csw.chatting.databinding.ActivityChatBinding
import com.google.firebase.messaging.ktx.remoteMessage
import deu.ac.kr.csw.chatting.user.model.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject


@AndroidEntryPoint
class ChatActivity : AppCompatActivity(), MessagesListAdapter.SelectionListener,
    MessagesListAdapter.OnLoadMoreListener, MessageInput.InputListener,
    MessageInput.AttachmentsListener, MessageInput.TypingListener {

    private lateinit var binding: ActivityChatBinding

    @Inject
    lateinit var messageRepository: MessageRepository

    @Inject
    lateinit var dialogRepository: DialogRepository

    private var imageLoader: ImageLoader =
        ImageLoader { imageView: ImageView?, url: String?, payload: Any? ->
            Picasso.get().load(url).into(imageView)
        }
    private var messagesAdapter: MessagesListAdapter<Message>? = null
    private var menu: Menu? = null
    private var selectionCount = 0
    private var lastLoadedDate: Date? = null
    private lateinit var dialogId: String
    private lateinit var dialog: Dialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        val input = findViewById<MessageInput>(R.id.input)
        input.setInputListener(this)
        input.setTypingListener(this)
        input.setAttachmentsListener(this)

        dialogId = intent.getStringExtra("dialogId")!!

        lifecycleScope.launch {
            dialog = dialogRepository.getDialog(dialogId)
            supportActionBar?.title = dialog.dialogName
        }
    }

    override fun onStart() {
        super.onStart()
        loadMessages(false)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        this.menu = menu
        menuInflater.inflate(R.menu.chat_actions_menu, menu)
        onSelectionChanged(0)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_delete -> messagesAdapter!!.deleteSelectedMessages()
            R.id.action_copy -> {
                messagesAdapter!!.copySelectedMessagesText(
                    this,
                    messageStringFormatter,
                    true
                )
                Toast.makeText(
                    this,
                    "Copied message",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        return true
    }

    override fun onBackPressed() {
        if (selectionCount == 0) {
            super.onBackPressed()
        } else {
            messagesAdapter!!.unselectAllItems()
        }
    }

    override fun onLoadMore(page: Int, totalItemsCount: Int) {
        Log.i("TAG", "onLoadMore: $page $totalItemsCount")
        if (totalItemsCount < TOTAL_MESSAGES_COUNT) {
            loadMessages(true)
        }
    }

    override fun onSelectionChanged(count: Int) {
        selectionCount = count
        menu!!.findItem(R.id.action_delete).isVisible =
            count > 0
        menu!!.findItem(R.id.action_copy).isVisible =
            count > 0
    }

    private fun loadMessages(useDelay: Boolean) {
        Log.d("ChatActivity", "loadMessages")
        val messages = ArrayList<Message>()
        messagesAdapter!!.addToEnd(messages, false)


        lifecycleScope.launch {
            delay(1000)
            messagesAdapter!!.clear(true)
            messageRepository.getMessageList(dialogId).collect() { it ->
                Log.d("ChatActivity", "message: $it")
                lateinit var user: User
                if (dialog.users.get(0).uid == it.user) {
                    user = dialog.users[0]
                } else {
                    user = dialog.users[1]
                }
                val message: Message = Message(
                    it.id,
                    user,
                    it.text,
                    it.createdAt,
                    it.systemGenerated
                )

                messagesAdapter!!.addToStart(message, true)
            }.let {
                Log.d("ChatActivity", "message: $it")
            }
        }
    }

    private val messageStringFormatter: MessagesListAdapter.Formatter<Message?>
        get() = object : MessagesListAdapter.Formatter<Message?> {
            override fun format(message: Message?): String? {
                if (message == null) return null
                val createdAt = SimpleDateFormat(
                    "MMM d, EEE 'at' h:mm a",
                    Locale.getDefault()
                )
                    .format(message.createdAt)
                var text = message.text
                if (text == null) text = "[attachment]"
                return String.format(
                    Locale.getDefault(), "%s: %s (%s)",
                    message.user.name, text, createdAt
                )
            }
        }

    companion object {
        private const val TOTAL_MESSAGES_COUNT = 100
    }

    override fun onSubmit(input: CharSequence): Boolean {
        //super.messagesAdapter!!.addToStart(
        lifecycle.coroutineScope.launch {
            messageRepository.sendMessage(
                dialogId,
                input.toString(),
                FirebaseAuth.getInstance().uid!!
            )
        }

        val fm = Firebase.messaging
        var fcmToken: String = ""
        if (FirebaseAuth.getInstance().uid == dialog.users[0].uid) {
            fcmToken = dialog.users[1].fcm
        } else {
            fcmToken = dialog.users[0].fcm
        }
        fm.send(
            com.google.firebase.messaging.RemoteMessage.Builder(fcmToken)
                .setMessageId("1")
                .addData("title", "title")
                .addData("body", input.toString())
                .build()
        )

        Log.d("ChatActivity", "result: , fcmToken: $fcmToken")

        return true
    }

    override fun onAddAttachments() {
        //super.messagesAdapter!!.addToStart(
    }

    private fun initAdapter() {
        messagesAdapter = MessagesListAdapter(
            FirebaseAuth.getInstance().uid!!,
            imageLoader
        )
        messagesAdapter!!.enableSelectionMode(this)
        messagesAdapter!!.setLoadMoreListener(this)
        messagesAdapter!!.registerViewClickListener(
            com.mikashboks.chatkit.R.id.messageUserAvatar,
            object : MessagesListAdapter.OnMessageViewClickListener<Message> {
                override fun onMessageViewClick(view: View?, message: Message) {
                    Toast.makeText(
                        this@ChatActivity,
                        message.user.name + " avatar click",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        binding.messagesList.setAdapter(messagesAdapter)
    }

    override fun onStartTyping() {
        Log.d(
            "Typing listener", "start typing"
        )
    }

    override fun onStopTyping() {
        Log.d(
            "Typing listener", "stop typing"
        )
    }


}