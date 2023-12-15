package deu.ac.kr.csw.chatting.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import deu.ac.kr.csw.chatting.user.model.User
import deu.ac.kr.csw.chatting.user.usecase.SetUserInfoUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegisterProfileViewModel @Inject constructor(
    private val setUserInfoUseCase: SetUserInfoUseCase
) : ViewModel() {


    fun setUserInfo(uid: String, user: User) : Unit {
        viewModelScope.launch {
            setUserInfoUseCase(uid, user)
        }
    }

}