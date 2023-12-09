package deu.ac.kr.csw.chatting.user

import androidx.lifecycle.MutableLiveData
import deu.ac.kr.csw.chatting.user.model.UserInfo

interface UserRepository {
    /**
     * 로그인 메소드
     * @param email String 이메일
     * @param password String 비밀번호
     *
     * @return 사용자 UID 반환
     */
    suspend fun login(email: String, password: String): String?

    /**
     * 구글 로그인 메소드
     *
     * @return 사용자 정보 반환
     */
    suspend fun loginWithGoogle(): String?

    /**
     * 회원가입 메소드
     *
     * @param email String 이메일
     * @param password String 비밀번호
     * @return 사용자 UID 반환 (성공 시) / null (실패 시)
     */
    suspend fun register(email: String, password: String): String?

    /**
     * 로그인된 유저의 UID를 가져오는 메소드
     * @return 로그인 된 유저의 UID 반환 (로그인 안되어 있으면 null 반환)
     */

    val userUid: MutableLiveData<String?>

    /**
     * 로그아웃 메소드
     *
     * @return 성공 여부 반환
     */
    suspend fun logout(): Boolean

    /**
     * 사용자 정보 업데이트 메소드
     * @param user UserInfo 사용자 정보
     * @return 업데이트 된 사용자 정보 반환
     */
    suspend fun setUserInfo(user: UserInfo): UserInfo?

    /**
     * 사용자 정보 가져오는 메소드
     * @param uid String 사용자 UID
     * @return 사용자 정보 반환
     */
    suspend fun getUserInfo(uid: String): UserInfo?
}