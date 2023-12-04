package deu.ac.kr.csw.chatting.user.usecase

import deu.ac.kr.csw.chatting.user.UserRepository
import javax.inject.Inject

class UserRegisterUseCase @Inject() constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(email: String, password: String) =
        userRepository.register(email, password)
}