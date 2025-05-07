package com.ssafy.trip.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ssafy.trip.exception.AuthException;
import com.ssafy.trip.exception.DuplicateIdException;
import com.ssafy.trip.exception.RecordNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateIdException.class)
    public ResponseEntity<?> handleDuplicateId(DuplicateIdException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(createFailResponse(e.getMessage()));
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<?> handleAuth(AuthException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(createFailResponse(e.getMessage()));
    }
    
    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<?> handleDuplicateId(RecordNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(createFailResponse(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneric(Exception e) {
        e.printStackTrace(); // 로그 용도
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(createFailResponse("서버 오류가 발생했습니다."));
    }

    // 공통 실패 응답 포맷 생성
    private static Object createFailResponse(String message) {
        return new java.util.HashMap<String, Object>() {{
            put("status", "FAIL");
            put("error", message);
        }};
    }
}
