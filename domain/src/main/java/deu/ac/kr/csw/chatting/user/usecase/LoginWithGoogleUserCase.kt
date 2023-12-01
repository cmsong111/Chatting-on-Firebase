package deu.ac.kr.csw.chatting.user.usecase

import deu.ac.kr.csw.chatting.user.UserRepository
import javax.inject.Inject

class LoginWithGoogleUserCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(idToken: String) =
        userRepository.loginWithGoogle(idToken)
}