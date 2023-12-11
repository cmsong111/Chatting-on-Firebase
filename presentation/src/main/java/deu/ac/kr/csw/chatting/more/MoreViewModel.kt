package deu.ac.kr.csw.chatting.more

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoreViewModel @Inject constructor() : ViewModel() {

    private val firebaseAuth = FirebaseAuth.getInstance()

    fun logout() {
        firebaseAuth.signOut()
    }

}