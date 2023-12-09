package deu.ac.kr.csw.chatting.user

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import deu.ac.kr.csw.chatting.user.model.UserInfo
import javax.inject.Inject

/**
 * UserRepository 구현체
 * Firebase를 사용하여 유저 정보를 관리한다.
 */
class UserRepositoryImpl @Inject constructor() : UserRepository {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    override val userUid: MutableLiveData<String?>
        get() = MutableLiveData(firebaseAuth.currentUser?.uid)

    override suspend fun register(email: String, password: String): String? {
        var result: String? = null
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                result = it.result?.user?.uid
            }
        }
        return result
    }

    override suspend fun login(email: String, password: String): String? {
        firebaseAuth.signInWithEmailAndPassword(email, password)
        return firebaseAuth.currentUser?.uid
    }

    override suspend fun setUserInfo(user: UserInfo): UserInfo? {
        firebaseFirestore.collection("users").document(user.uid).set(user)
        return null
    }

    override suspend fun getUserInfo(uid: String): UserInfo? {
        // TODO: 사용자 정보 가져오기 구현
        return null
    }

    override suspend fun loginWithGoogle(): String? {
        // TODO: 구글 로그인 구현
        return null
    }

    override suspend fun logout(): Boolean {
        firebaseAuth.signOut()
        return true
    }

}