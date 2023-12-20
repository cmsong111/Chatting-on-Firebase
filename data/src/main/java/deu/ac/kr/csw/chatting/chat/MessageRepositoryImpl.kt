package deu.ac.kr.csw.chatting.chat

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObjects
import deu.ac.kr.csw.chatting.chat.model.Message
import deu.ac.kr.csw.chatting.chat.model.MessageEntity
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.util.Date
import javax.inject.Inject

class MessageRepositoryImpl @Inject constructor() : MessageRepository {

    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    override suspend fun getMessageList(dialogId: String): Flow<MessageEntity> {

        createCollectionIfNotExists(dialogId)

        return callbackFlow {
            firebaseFirestore.collection("dialogs").document(dialogId).collection("messages")
                .addSnapshotListener { value, error ->
                    if (error != null) {
                        close(error)
                    } else {
                        value?.toObjects<MessageEntity>()?.forEach {
                            trySend(it).isSuccess
                        }
                    }
                }.let { awaitClose { it.remove() } }
        }
    }

    override suspend fun sendMessage(dialogId: String, message: String, senderUid: String) {
        val messageEntity = MessageEntity(
            id = "",
            text = message,
            createdAt = Date(),
            unread = true,
            systemGenerated = false,
            user = senderUid,
        )

        firebaseFirestore.collection("dialogs").document(dialogId).collection("messages")
            .add(messageEntity).await().let { documentReference ->
                documentReference.update("id", documentReference.id)
            }
    }

    override suspend fun readMessage(dialogId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMessage(dialogId: String, messageId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun editMessage(dialogId: String, messageId: String, message: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getMessage(dialogId: String, messageId: String): Message {
        TODO("Not yet implemented")
    }

    suspend fun createCollectionIfNotExists(dialogId: String) {
        val messagesCollection =
            firebaseFirestore.collection("dialogs").document(dialogId).collection("messages")

        // Check if the collection exists
        val collectionSnapshot = messagesCollection.limit(1).get().await()

        if (collectionSnapshot.isEmpty) {
            // Collection doesn't exist, create it and add a default message
            val defaultMessage = MessageEntity(
                id = "",
                text = "채팅방이 생성되었습니다.",
                createdAt = Date(),
                unread = true,
                systemGenerated = true,
                user = "",
            )

            messagesCollection.add(defaultMessage).await()
        }
    }
}