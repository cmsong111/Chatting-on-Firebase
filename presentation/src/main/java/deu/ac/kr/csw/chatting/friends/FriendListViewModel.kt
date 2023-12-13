package deu.ac.kr.csw.chatting.friends

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import deu.ac.kr.csw.chatting.user.model.User
import javax.inject.Inject


@HiltViewModel
class FriendListViewModel @Inject constructor(
) : ViewModel() {
    var friends = ArrayList<User>()
    var friendListAdapter = FriendListAdapter(friends)


    // TODO: Firebase에서 친구 목록을 가져와서 friends에 넣어주는 코드를 작성해야 합니다.
    init {
        for (i in 1..100) {
            friends.add(
                User(
                    "email$i@domain.com",
                    "name$i",
                    "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png",
                    true,
                    "fcmToken$i",
                    "status Message $i"
                )
            )
        }
    }
}
