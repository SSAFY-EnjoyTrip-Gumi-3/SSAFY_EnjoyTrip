package com.ssafy.trip.security;

import org.springframework.stereotype.Component;

import com.ssafy.trip.exception.AuthException;
import com.ssafy.trip.model.dto.UserResponse;

import jakarta.servlet.http.HttpSession;

@Component
public class AuthContext {

    public UserResponse getCurrentUser(HttpSession session) {
        UserResponse user = (UserResponse) session.getAttribute("loginUser");
        if (user == null) {
            throw new AuthException("로그인이 필요합니다.");
        }
        return user;
    }

    public int getCurrentUserNo(HttpSession session) {
        return getCurrentUser(session).getUserNo();
    }

    public String getCurrentUserId(HttpSession session) {
        return getCurrentUser(session).getId();
    }
}
