package deu.ac.kr.csw.chatting.auth

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import deu.ac.kr.csw.chatting.user.usecase.IsLoginUseCase
import deu.ac.kr.csw.chatting.user.usecase.LoginWithGoogleUserCase
import deu.ac.kr.csw.chatting.user.usecase.UserLoginUseCase
import kotlinx.coroutines.launch
import java.util.concurrent.Flow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginWithGoogleUserCase: LoginWithGoogleUserCase,
    private val userLoginUseCase: UserLoginUseCase,
    private val isLonginUseCase: IsLoginUseCase
) : ViewModel() {

    val email: MutableLiveData<String> = MutableLiveData("")
    val password: MutableLiveData<String> = MutableLiveData("")

    fun login() {
        viewModelScope.launch {
            Log.d("LoginViewModel", "login: ${email.value} ${password.value}")
            userLoginUseCase(email.value!!, password.value!!)

            if (isLonginUseCase().value!!) {
                Log.d("LoginViewModel", "login: ${isLonginUseCase().value}")

            } else {
                Log.d("LoginViewModel", "login: ${isLonginUseCase().value}")
            }
        }
    }

    fun register() {
        Log.d("LoginViewModel", "register: ${email.value} ${password.value}")
    }

    fun loginWithGoogle() {

    }

}