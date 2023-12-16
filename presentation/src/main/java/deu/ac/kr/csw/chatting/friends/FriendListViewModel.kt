package deu.ac.kr.csw.chatting.friends

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
    var friends : List<User> = emptyList()
    var friendListAdapter = FriendListAdapter(friends)


    // TODO: Firebase에서 친구 목록을 가져와서 friends에 넣어주는 코드를 작성해야 합니다.
    init {
        viewModelScope.launch {
            userRepository.getUserList().let {
                friendListAdapter.setFriends(it)
            }
        }
    }
}
