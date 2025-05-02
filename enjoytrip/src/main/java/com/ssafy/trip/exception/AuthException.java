package com.ssafy.trip.exception;

@SuppressWarnings("serial")
public class AuthException extends RuntimeException{
	public AuthException(String msg) {
		super(msg);
	}
}
