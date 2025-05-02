package com.ssafy.trip.exception;

@SuppressWarnings("serial")
public class DuplicateIdException extends RuntimeException {
    public DuplicateIdException(String message) {
        super(message);
    }
}
