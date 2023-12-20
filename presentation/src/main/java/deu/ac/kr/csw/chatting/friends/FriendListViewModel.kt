package deu.ac.kr.csw.chatting.friends

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import deu.ac.kr.csw.chatting.user.UserRepository
import deu.ac.kr.csw.chatting.user.model.User
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FriendListViewModel @Inject constructor(
    val userRepository: UserRepository
) : ViewModel() {
    var friends: List<User> = emptyList()
    var friendListAdapter = FriendListAdapter(friends)

    init {
        viewModelScope.launch {
            userRepository.getUserList().let {
                friendListAdapter.setFriends(it)
            }
        }
    }
}
