package com.ssafy.trip.exception;

@SuppressWarnings("serial")
public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException(String message) {
        super(message);
    }
}
