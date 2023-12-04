package deu.ac.kr.csw.chatting.user

import androidx.lifecycle.MutableLiveData
import deu.ac.kr.csw.chatting.user.model.UserInfo

interface UserRepository {
    suspend fun login(email: String, password: String): UserInfo?
    suspend fun loginWithGoogle(idToken: String): UserInfo?
    suspend fun register(email: String, password: String, name: String): UserInfo?
    suspend fun updateUserProfile(user: UserInfo) : UserInfo?

    val isLogin : MutableLiveData<Boolean>
//    suspend fun loginWithGoogle(idToken: String): User?
//    suspend fun getUser(uid: String): User?

//    suspend fun getFriends(): List<User>
}