package deu.ac.kr.csw.chatting.user

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import deu.ac.kr.csw.chatting.user.model.UserInfo
import kotlinx.coroutines.tasks.await
import java.util.concurrent.Flow
import javax.inject.Inject

/**
 * UserRepository 구현체
 * Firebase를 사용하여 유저 정보를 관리한다.
 * @property isLogin MutableLiveData<Boolean> 로그인 여부를 저장하는 LiveData
 */
class UserRepositoryImpl @Inject constructor() : UserRepository {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    override val isLogin: MutableLiveData<Boolean>
        get() = MutableLiveData(firebaseAuth.currentUser != null)

    override suspend fun register(email: String, password: String): Boolean {
        var result = false
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                firebaseFirestore.collection("users").document(it.result.user!!.uid).set(
                    UserInfo(
                        name = it.result.user!!.displayName!!,
                        email = it.result.user!!.email!!,
                        uid = it.result.user!!.uid,
                        profileImageUrl = it.result.user!!.photoUrl.toString(),
                        statusMessage = ""
                    )
                )
                result = true
            }
        }
        return result
    }

    override suspend fun login(email: String, password: String): UserInfo? {
        firebaseAuth.signInWithEmailAndPassword(email, password)
        isLogin.postValue(true)
        var userInfo: UserInfo? = null
        val user = firebaseAuth.currentUser
        if (user != null) {
            userInfo =  UserInfo(
                email = email,
                name = "not set",
                uid = "",
                profileImageUrl = "",
                statusMessage = password
            )
        }
        return userInfo
    }

    override suspend fun updateUserProfile(user: UserInfo): UserInfo? {
        firebaseFirestore.collection("users").document(user.uid).set(user)
        return null
    }

    override suspend fun loginWithGoogle(idToken: String): UserInfo? {
        val credential = firebaseAuth.signInWithCredential(
            GoogleAuthProvider.getCredential(idToken, null)
        ).await()
        val user = credential.user
        var userInfo: UserInfo? = null
        if (user != null) {
            isLogin.postValue(true)
            val userDoc = firebaseFirestore.collection("users").document(user.uid).get().await()
            if (!userDoc.exists()) {
                userInfo = UserInfo(
                    email = user.email!!,
                    name = user.displayName!!,
                    uid = user.uid,
                    profileImageUrl = user.photoUrl.toString(),
                    statusMessage = ""
                )
                firebaseFirestore.collection("users").document(user.uid).set(userInfo)
            }
        }
        return userInfo
    }

    override suspend fun logout() {
        firebaseAuth.signOut()
        isLogin.postValue(false)
    }

}