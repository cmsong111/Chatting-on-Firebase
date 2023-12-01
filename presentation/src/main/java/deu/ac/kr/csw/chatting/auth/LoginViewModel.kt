package deu.ac.kr.csw.chatting.auth

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import deu.ac.kr.csw.chatting.user.usecase.LoginWithGoogleUserCase
import deu.ac.kr.csw.chatting.user.usecase.UserLoginUseCase
import kotlinx.coroutines.launch
import java.util.concurrent.Flow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginWithGoogleUserCase: LoginWithGoogleUserCase,
    private val userLoginUseCase: UserLoginUseCase
) : ViewModel() {

    val  email : MutableLiveData<String> = MutableLiveData("")
    val  password : MutableLiveData<String> = MutableLiveData("")

    fun login() {
        viewModelScope.launch {
            Log.d("LoginViewModel", "login: ${email.value} ${password.value}")
            userLoginUseCase(email.value!!, password.value!!)
        }
    }

    fun loginWithGoogle(idToken: String) {
        viewModelScope.launch {
            loginWithGoogleUserCase(idToken)
        }
    }
}