package deu.ac.kr.csw.chatting.more

import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import deu.ac.kr.csw.chatting.user.UserRepository
import deu.ac.kr.csw.chatting.user.model.User
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoreViewModel @Inject constructor(
    private var userRepository: UserRepository
) : ViewModel() {

    private val firebaseAuth = FirebaseAuth.getInstance()


    val userName = MutableLiveData<String>()

    val userStateMessage = MutableLiveData<String>()

    val userAvatar = MutableLiveData<String>()


    fun logout() {
        firebaseAuth.signOut()
    }

    fun setUserInfo() {
        viewModelScope.launch {
            val user = userRepository.getUserInfo(firebaseAuth.currentUser!!.uid)!!
            user.name = userName.value!!
            user.statusMessage = userStateMessage.value!!
            user.avatar = userAvatar.value!!

            userRepository.setUserInfo(user)

        }
    }

    init {
        viewModelScope.launch {
            val user = userRepository.getUserInfo(firebaseAuth.currentUser!!.uid)!!
            userName.value = user.name
            userStateMessage.value = user.statusMessage
            userAvatar.value = user.avatar
        }

    }
}