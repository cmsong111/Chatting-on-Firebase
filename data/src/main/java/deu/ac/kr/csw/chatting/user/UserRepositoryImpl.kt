package deu.ac.kr.csw.chatting.user

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import deu.ac.kr.csw.chatting.user.model.User
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

    override suspend fun register(email: String, password: String, name: String): User? {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                val user = User(
                    email = email,
                    name = name,
                    uid = it.result?.user?.uid!!,
                    profileImageUrl = it.result.user?.photoUrl.toString()
                )
                firebaseFirestore.collection("users").document(user.uid).set(user)
            }
        }
        return null
    }

    override suspend fun login(email: String, password: String): User? {
        firebaseAuth.signInWithEmailAndPassword(email, password)
        isLogin.postValue(true)
        return null
    }

    override suspend fun updateUserProfile(user: User): User? {
        firebaseFirestore.collection("users").document(user.uid).set(user)
        return null
    }

    override suspend fun loginWithGoogle(idToken: String): User? {
        val credential = firebaseAuth.signInWithCredential(
            GoogleAuthProvider.getCredential(idToken, null)
        ).await()
        val user = credential.user
        if (user != null) {
            isLogin.postValue(true)
            val userDoc = firebaseFirestore.collection("users").document(user.uid).get().await()
            if (!userDoc.exists()) {
                val newUser = User(
                    email = user.email!!,
                    name = user.displayName!!,
                    uid = user.uid,
                    profileImageUrl = user.photoUrl.toString()
                )
                firebaseFirestore.collection("users").document(user.uid).set(newUser)
            }
        }
        return null
    }


}