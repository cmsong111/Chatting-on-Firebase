package deu.ac.kr.csw.chatting.chat

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

    override suspend fun getDialogList(uid: String): List<Dialog> {
        val dialogList: List<Dialog> = emptyList()
        firebaseFirestore.collection("dialog").whereArrayContains("users", uid).get().await().let {
            it.documents.forEach { document ->
                val dialog = document.toObject(DialogEntity::class.java).let {
                    var dialog: Dialog = toDto(it!!)
                    dialog.id = document.id
                    dialogList.plus(dialog)
                }
            }
        }
        return dialogList
    }

    override suspend fun getDialog(myUid: String, yourUid: String): Dialog {
        val dialogList: List<Dialog> = emptyList()
        firebaseFirestore.collection("dialogs")
            .whereArrayContainsAny("users", listOf(myUid, yourUid)).get().await().let {
                it.documents.forEach { document ->
                    val dialog = document.toObject(DialogEntity::class.java).let {
                        var dialog: Dialog = toDto(it!!)
                        dialog.id = document.id
                        dialogList.plus(dialog)
                    }
                }
            }

        if (dialogList.isEmpty()) {
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
        return dialogList[0]
    }

    private suspend fun toDto(dialogentity: DialogEntity): Dialog {
        var users: ArrayList<User> = ArrayList()

        dialogentity.users.forEach() {
            users.add(
                firebaseFirestore.collection("users").document(it)
                    .get().await().toObject(User::class.java)!!
            )
        }

        return Dialog(
            "id",
            dialogentity.dialogPhoto,
            dialogentity.dialogName,
            users,
            Message("Stf", users[0], "Hello", Date()),
            dialogentity.unreadCount
        )
    }
}