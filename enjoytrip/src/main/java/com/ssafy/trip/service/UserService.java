package com.ssafy.trip.service;

import com.ssafy.trip.model.dto.User;

/**
 * <h2>UserService — 비즈니스 규칙 계층</h2>
 * <p>
 * Controller ↔ DAO 사이에서<br>
 *  • 인증·권한·검증<br>
 *  • 트랜잭션 처리<br>
 *  • 예외 변환<br>
 *  • 부가 로직(암호화·휴면전환 등)<br>
 * 을 담당한다.
 * </p>
 */
public interface UserService {

    /* ───────────────────────── 로그인 / 인증 ───────────────────────── */

    /**
     * 로그인 (ID + 비밀번호 검증)<br>
     * <ul>
     *   <li>비밀번호 해시 매칭</li>
     *   <li>계정 상태 체크(탈퇴·휴면 등)</li>
     *   <li>성공 시 {@code last_login} 갱신</li>
     * </ul>
     *
     * @param id        로그인 ID
     * @param password  평문 비밀번호
     * @return          정상 로그인된 {@link User}
     * @throws com.ssafy.trip.exception.AuthException
     *         인증 실패(아이디·비밀번호·상태 문제) 시
     */
    User login(String id, String password);

    /* ───────────────────────── 회원 등록 / 수정 ───────────────────────── */

    /**
     * 회원 가입
     *
     * @param user  가입 정보 
     * @return      INSERT 행 수(1)
     */
    int registUser(User user);

    /**
     * 회원 정보 수정
     *
     * @param user  수정 DTO (userNo 필수)
     * @return      UPDATE 행 수
     */
    int updateUser(User user);

    /* ───────────────────────── 단건 조회 / 탈퇴 ───────────────────────── */

    /**
     * ID로 회원 조회
     *
     * @param id  로그인 ID
     * @return    {@link User}
     * @throws com.ssafy.trip.exception.RecordNotFoundException
     *         존재하지 않을 때
     */
    User getUser(String id);

    /**
     * 탈퇴(Soft Delete) 처리 → status='DELETED'
     *
     * @param userNo  사용자 PK
     * @return        UPDATE 행 수
     */
    int deleteUser(int userNo);

    /* ───────────────────────── 검증 / 상태 전환 ───────────────────────── */

    /** ID 중복 여부 */
    boolean checkIdDuplicate(String id);

    /** 휴면 전환(status='INACTIVE') */
    int deactivateUser(int userNo);

    /** 휴면 복구(status='ACTIVE') */
    int activateUser(int userNo);

    /*  필요 시 관리자·통계용 메서드 추가 예시 
     *
     *  List<User> getAllUsers();
     *  List<User> getInactiveUsers();
     *  List<User> getActiveUsers();
     *  List<User> getDeletedUsers();
     */
}
