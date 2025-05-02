package com.ssafy.trip.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.trip.exception.AuthException;
import com.ssafy.trip.exception.DuplicateIdException;
import com.ssafy.trip.exception.RecordNotFoundException;
import com.ssafy.trip.model.dao.UserDao;
import com.ssafy.trip.model.dto.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final UserDao uDao;

	@Override
	@Transactional
	public int registUser(User user) {
		
		if(uDao.existsById(user.getId())) {
			throw new DuplicateIdException("이미 존재하는 ID 입니다.");
		}
		
		// 기본 권한, 상태 지정
		if (user.getRole() == null)   user.setRole("USER");
	    if (user.getStatus() == null) user.setStatus("ACTIVE");

		return uDao.insert(user);
	}

	@Override
	@Transactional
	public int updateUser(User user) {
		return uDao.update(user);
	}

	@Override
	public User getUser(String id) {
		if(!uDao.existsById(id)) {
			throw new RecordNotFoundException("존재하지 않는 회원입니다.");
		}
		
		return uDao.select(id);
	}

	@Override
	@Transactional
	public int deleteUser(int userNo) {
		return uDao.delete(userNo);
	}

	@Override
	public boolean checkIdDuplicate(String id) {
		return uDao.existsById(id);
	}

	@Override
	public int deactivateUser(int userNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int activateUser(int userNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional
	public User login(String id, String password) {

	    User user = uDao.select(id);

	    if (user == null || !user.getPassword().equals(password)) {
	        throw new AuthException("아이디 또는 비밀번호가 틀립니다.");
	    }

	    switch (user.getStatus()) {
	        case "DELETED"  -> throw new AuthException("이미 탈퇴 처리된 계정입니다.");
	        case "INACTIVE" -> throw new AuthException("휴면 계정입니다. 본인 확인 후 복구해 주세요.");
	        case "ACTIVE"   -> {
	            uDao.updateLastLogin(user.getUserNo());
	            return user;
	        }
	        default         -> throw new AuthException("계정 상태가 올바르지 않습니다.");
	    }
	}


}
