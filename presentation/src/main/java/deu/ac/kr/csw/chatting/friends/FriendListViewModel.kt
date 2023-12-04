package deu.ac.kr.csw.chatting.friends

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import deu.ac.kr.csw.chatting.user.model.UserInfo
import javax.inject.Inject


@HiltViewModel
class FriendListViewModel @Inject constructor(
) : ViewModel() {
    var friends = ArrayList<UserInfo>()
    var friendListAdapter = FriendListAdapter(friends)


    // TODO: Firebase에서 친구 목록을 가져와서 friends에 넣어주는 코드를 작성해야 합니다.
    init {
        for (i in 1..100) {
            friends.add(
                UserInfo(
                    uid = "uid$i",
                    email = "email$i@test.com",
                    name = "name$i",
                    profileImageUrl = "profileImageUrl$i",
                    statusMessage = "statusMessage$i"
                )
            )
        }
    }
}
