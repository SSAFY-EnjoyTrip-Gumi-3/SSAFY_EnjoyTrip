package com.ssafy.trip.model.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.ssafy.trip.model.dto.User;

/**
 * 사용자 관련 DB 접근 로직을 정의한 MyBatis Mapper 인터페이스.
 * <p>
 *  CRUD + 휴면/복구 + 로그인 시각 갱신 등
 *  User 엔티티와 관련된 대부분의 쿼리를 한 곳에 모아 둔다.
 * </p>
 */
@Mapper
public interface UserDao {

    /** 
     * 회원 가입  
     *
     * @param user  저장할 사용자 DTO (password는 암호화된 상태여야 함)
     * @return      INSERT된 행 수(성공=1)  
     */
    int insert(User user);

    /** 
     * 회원 정보 수정  
     *
     * @param user  수정할 값이 포함된 DTO (userNo 필수)  
     * @return      UPDATE된 행 수(성공=1, 실패=0)  
     */
    int update(User user);

    /**
     * 아이디(ID)로 회원 단건 조회  
     *
     * @param id   로그인 ID  
     * @return     존재하면 User, 없으면 {@code null}
     */
    User select(String id);

    /** 
     * 소프트-삭제(탈퇴) 처리 → status='DELETED' 로 변경  
     *
     * @param userNo  사용자 PK  
     * @return        UPDATE된 행 수  
     */
    int delete(int userNo);

    /**
     * ID 중복 여부 검사  
     *
     * @param id  확인할 ID  
     * @return    이미 존재하면 {@code true}, 없으면 {@code false}
     */
    boolean existsById(String id);

    /**
     * 휴면 전환 (status='INACTIVE')  
     *
     * @param userNo  대상 사용자 PK  
     * @return        UPDATE된 행 수
     */
    int deactivate(int userNo);

    /**
     * 휴면 복구 (status='ACTIVE')  
     *
     * @param userNo  대상 사용자 PK  
     * @return        UPDATE된 행 수
     */
    int activate(int userNo);

    /**
     * 마지막 로그인 시각을 NOW()로 갱신  
     *
     * @param userNo  대상 사용자 PK  
     * @return        UPDATE된 행 수
     */
    int updateLastLogin(int userNo);

    /* ---------- 관리자/통계용 조회 ---------- */

    /** 모든 회원 전체 조회 */
    List<User> selectAll();

    /** 휴면(INACTIVE) 회원 목록 조회 */
    List<User> selectInactiveUsers();

    /** 활성(ACTIVE) 회원 목록 조회 */
    List<User> selectActiveUsers();

    /** 탈퇴(DELETED) 회원 목록 조회 */
    List<User> selectDeleteUsers();
}
