package deu.ac.kr.csw.chatting.model

class Room(

    var id: String,
    var name: String,
    var createdAt: Long,
    var users: ArrayList<String>,
    var chats: ArrayList<Chat>
) {

        override fun toString(): String {
            return "Room(id='$id', name='$name', createdAt=$createdAt, users=$users)"
        }
}