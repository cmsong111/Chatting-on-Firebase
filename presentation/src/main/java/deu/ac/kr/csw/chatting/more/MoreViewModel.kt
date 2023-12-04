package deu.ac.kr.csw.chatting.more

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import deu.ac.kr.csw.chatting.user.usecase.IsLoginUseCase
import deu.ac.kr.csw.chatting.user.usecase.UserLogoutUserCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoreViewModel @Inject constructor(
    private val logoutUserCase: UserLogoutUserCase,
    private val isLoginUseCase: IsLoginUseCase
) : ViewModel() {

    fun logout() {
        viewModelScope.launch {
            logoutUserCase()
        }
    }




}