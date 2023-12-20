package deu.ac.kr.csw.chatting.chat.model

import deu.ac.kr.csw.chatting.user.model.User

class DialogEntity(
    var dialogPhoto: String,
    var dialogName: String,
    var users: List<String>,
    var lastMessage: String,
    var unreadCount: Int = 0
) {
}