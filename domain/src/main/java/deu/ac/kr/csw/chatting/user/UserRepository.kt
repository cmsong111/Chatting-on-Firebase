package deu.ac.kr.csw.chatting.user

import androidx.lifecycle.MutableLiveData
import deu.ac.kr.csw.chatting.user.model.User

interface UserRepository {
    suspend fun login(email: String, password: String): User?
    suspend fun loginWithGoogle(idToken: String): User?
    suspend fun register(email: String, password: String, name: String): User?
    suspend fun updateUserProfile(user: User) : User?

    val isLogin : MutableLiveData<Boolean>
//    suspend fun loginWithGoogle(idToken: String): User?
//    suspend fun getUser(uid: String): User?

//    suspend fun getFriends(): List<User>
}