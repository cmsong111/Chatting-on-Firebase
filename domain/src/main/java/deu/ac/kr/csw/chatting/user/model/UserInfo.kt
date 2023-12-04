package deu.ac.kr.csw.chatting.user.model

 class UserInfo(
    var uid: String,
    var email: String,
    var name: String,
    var profileImageUrl: String,
    var statusMessage: String,
    val pushToken: String? = null
){
    override fun toString(): String {
        return "UserInfo(uid='$uid', email='$email', name='$name', profileImageUrl='$profileImageUrl', statusMessage='$statusMessage', pushToken=$pushToken)"
    }
 }