package deu.ac.kr.csw.chatting.user

import androidx.lifecycle.MutableLiveData
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

import com.google.firebase.firestore.FirebaseFirestore
import deu.ac.kr.csw.chatting.user.model.UserInfo
import javax.inject.Inject

/**
 * UserRepository 구현체
 * Firebase를 사용하여 유저 정보를 관리한다.
 */
class UserRepositoryImpl @Inject constructor() : UserRepository {

    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    override suspend fun setUserInfo(user: UserInfo): UserInfo? {
        firebaseFirestore.collection("users").document(user.uid).set(user)
        return null
    }

    override suspend fun getUserInfo(uid: String): UserInfo? {
        // TODO: 사용자 정보 가져오기 구현
        return null
    }
}