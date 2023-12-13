package deu.ac.kr.csw.chatting.user

import deu.ac.kr.csw.chatting.user.model.User

interface UserRepository {

    /**
     * 사용자 정보 업데이트 메소드
     * @param user UserInfo 사용자 정보
     * @return 업데이트 된 사용자 정보 반환
     */
    suspend fun setUserInfo(user: User): User?

    /**
     * 사용자 정보 가져오는 메소드
     * @param uid String 사용자 UID
     * @return 사용자 정보 반환
     */
    suspend fun getUserInfo(uid: String): User?
}