package deu.ac.kr.csw.chatting.user.usecase

import deu.ac.kr.csw.chatting.user.UserRepository
import javax.inject.Inject

class IsLoginUseCase @Inject() constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke() =
        userRepository.isLogin
}
