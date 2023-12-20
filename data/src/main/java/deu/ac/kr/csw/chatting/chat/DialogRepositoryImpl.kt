package deu.ac.kr.csw.chatting.chat

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import deu.ac.kr.csw.chatting.chat.model.Dialog
import deu.ac.kr.csw.chatting.chat.model.DialogEntity
import deu.ac.kr.csw.chatting.chat.model.Message
import deu.ac.kr.csw.chatting.user.model.User
import kotlinx.coroutines.tasks.await
import java.util.Date
import javax.inject.Inject

class DialogRepositoryImpl @Inject constructor(
) : DialogRepository {

    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    override suspend fun getDialogList(uid: String): ArrayList<Dialog> {
        val dialogList: ArrayList<Dialog> = ArrayList()
        firebaseFirestore.collection("dialogs").whereArrayContains("users", uid).get().await().let {
            it.documents.forEach { document ->
                document.toObject(DialogEntity::class.java).let { dialogentity ->
                    Log.d("DialogRepositoryImpl", "dialogentity: $dialogentity")
                    var dialog: Dialog = toDto(dialogentity!!)
                    dialog.id = document.id
                    dialogList.add(dialog)
                }
            }
        }
        // log len
        Log.d("DialogRepositoryImpl", "dialogList: $dialogList")
        Log.d("DialogRepositoryImpl", "dialogList.size: ${dialogList.size}")

        return dialogList
    }

    override suspend fun getDialog(myUid: String, yourUid: String): Dialog {

        firebaseFirestore.collection("dialogs").whereArrayContains("users", myUid).get().await().let {
            it.documents.forEach { document ->
                document.toObject(DialogEntity::class.java).let { dialogentity ->
                    if (document.toObject(DialogEntity::class.java)!!.users.contains(yourUid)) {
                        var dialog: Dialog = toDto(dialogentity!!)
                        dialog.id = document.id
                        return dialog
                    }
                }
            }
        }

        Log.d("DialogRepositoryImpl", "Dialog is not exist")
        DialogEntity(
            "https://m.media-amazon.com/images/I/31VjU29FP+L.png",
            "채팅방",
            listOf(myUid, yourUid),
            "ss",
            0,
        ).let {
            firebaseFirestore.collection("dialogs").add(it).await()
            return toDto(it)
        }


    }

    private suspend fun toDto(dialogentity: DialogEntity): Dialog {
        var users: ArrayList<User> = ArrayList()

        dialogentity.users.forEach() {
            users.add(
                firebaseFirestore.collection("users").document(it)
                    .get().await().toObject(User::class.java)!!
            )
        }

        // TODO: lastMessage 조회

        return Dialog(
            "id",
            dialogentity.dialogName,
            dialogentity.dialogPhoto,
            users,
            Message("Stf", users[0], "Hello", Date()),
            dialogentity.unreadCount
        )
    }
}