package deu.ac.kr.csw.chatting.chat

import deu.ac.kr.csw.chatting.chat.model.Dialog

interface DialogRepository {

    /**
     * 내가 속한 채팅방 리스트를 가져오는 메소드
     * @param uid String 내 UID
     */
    suspend fun getDialogList(uid: String): List<Dialog>

    /**
     * 채팅방 생성 메소드
     *
     * 만약 채팅방이 존재한다면 존재하는 채팅방을 반환한다.
     *
     * @param myUid String 내 UID
     * @param yourUid String 상대방 UID
     */
    suspend fun getDialog(myUid: String, yourUid: String): Dialog
}