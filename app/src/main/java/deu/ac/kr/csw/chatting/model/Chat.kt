package deu.ac.kr.csw.chatting.model

class Chat (
    var id : String,
    var author : String,
    var data : String,
    var createdAt : Long
){

    override fun toString(): String {
        return "Chat(id='$id', author='$author', data='$data', createdAt=$createdAt)"
    }
}