package deu.ac.kr.csw.chatting.user.usecase

import deu.ac.kr.csw.chatting.user.UserRepository
import deu.ac.kr.csw.chatting.user.model.UserInfo
import javax.inject.Inject

class SetUserInfoUseCase @Inject() constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(user: UserInfo) =
        userRepository.setUserInfo(user)
}