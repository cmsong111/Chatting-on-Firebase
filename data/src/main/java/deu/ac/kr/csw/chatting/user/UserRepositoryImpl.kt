package deu.ac.kr.csw.chatting.user

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObjects
import deu.ac.kr.csw.chatting.user.model.User
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/**
 * UserRepository 구현체
 * Firebase를 사용하여 유저 정보를 관리한다.
 */
class UserRepositoryImpl @Inject constructor() : UserRepository {

    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    override suspend fun setUserInfo(uid: String, user: User): User {
        firebaseFirestore.collection("users").document(uid).set(user)
        return user
    }

    override suspend fun getUserInfo(uid: String): User? {
        val user = firebaseFirestore.collection("users").document(uid).get().result?.toObject(User::class.java)
        return user
    }

    override suspend fun getUserList(): List<User> {
        return  firebaseFirestore.collection("users").get().await().toObjects<User>()
    }


}