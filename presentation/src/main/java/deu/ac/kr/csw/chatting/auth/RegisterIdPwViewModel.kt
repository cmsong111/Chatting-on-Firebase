package deu.ac.kr.csw.chatting.auth

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import deu.ac.kr.csw.chatting.user.UserRepository
import deu.ac.kr.csw.chatting.user.usecase.UserRegisterUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterIdPwViewModel @Inject constructor(
    private val userRegisterUseCase: UserRegisterUseCase,
    private val userRepository: UserRepository
) : ViewModel() {


    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val passwordCheck = MutableLiveData<String>()


    // 결과 반환 MutableLiveData
    fun register() : MutableLiveData<String?> {
        viewModelScope.launch {
            Log.d("RegisterIdPwViewModel", "register: ${email.value} ${password.value}")
            userRegisterUseCase(email.value!!, password.value!!).let {
                Log.d("RegisterIdPwViewModel", "register: $it")
            }
        }
        return userRepository.userUid
    }
}