package com.user.exception;

import org.springframework.stereotype.Component;

@Component
public class IncorrectCredentialException extends RuntimeException{

	
	public IncorrectCredentialException() {
	
	}
	
	public IncorrectCredentialException(String msg) {
	
		super(msg);
	}
}
