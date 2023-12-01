package deu.ac.kr.csw.chatting.user.model

class User(
    val uid: String,
    val name: String,
    val email: String,
    val profileImageUrl: String,
    val pushToken: String? = null
)