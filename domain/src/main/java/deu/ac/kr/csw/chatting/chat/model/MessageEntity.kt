package deu.ac.kr.csw.chatting.chat.model

import deu.ac.kr.csw.chatting.user.model.User
import java.util.Date

class MessageEntity(
    var id: String,
    var text: String,
    var createdAt: Date,
    var unread: Boolean,
    var systemGenerated: Boolean,
    var user: String,
) {

    constructor() : this("", "", Date(), false, false, "")

    override fun toString(): String {
        return "MessageEntity(id='$id', text='$text', createdAt=$createdAt, unread=$unread, systemGenerated=$systemGenerated, user='$user')"
    }
}