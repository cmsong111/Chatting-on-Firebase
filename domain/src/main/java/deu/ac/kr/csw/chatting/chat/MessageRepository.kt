package deu.ac.kr.csw.chatting.chat


import deu.ac.kr.csw.chatting.chat.model.Message
import deu.ac.kr.csw.chatting.chat.model.MessageEntity
import kotlinx.coroutines.flow.Flow


interface MessageRepository {

    /**
     * 채팅방의 메시지 리스트를 가져오는 메소드
     * @param dialogId String 채팅방 ID
     */
    suspend fun getMessageList(dialogId: String): Flow<MessageEntity>

    /**
     * 메시지를 보내는 메소드
     *
     * @param dialogId String 채팅방 ID
     * @param message String 메시지
     */
    suspend fun sendMessage(dialogId: String, message: String, senderUid: String)

    /**
     * 메시지를 읽음 처리하는 메소드
     *
     * @param dialogId String 채팅방 ID
     */
    suspend fun readMessage(dialogId: String)

    /**
     * 메시지를 삭제하는 메소드
     *
     * @param dialogId String 채팅방 ID
     * @param messageId String 메시지 ID
     */
    suspend fun deleteMessage(dialogId: String, messageId: String)

    /**
     * 메시지를 수정하는 메소드
     *
     * @param dialogId String 채팅방 ID
     * @param messageId String 메시지 ID
     * @param message String 메시지
     */
    suspend fun editMessage(dialogId: String, messageId: String, message: String)

    /**
     * 메시지를 가져오는 메소드
     *
     * @param dialogId String 채팅방 ID
     * @param messageId String 메시지 ID
     */
    suspend fun getMessage(dialogId: String, messageId: String): Message

}