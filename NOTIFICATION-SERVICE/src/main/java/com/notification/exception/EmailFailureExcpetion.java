package com.notification.exception;

public class EmailFailureExcpetion extends RuntimeException{

	public EmailFailureExcpetion() {
		
	}

	public EmailFailureExcpetion(String msg) {
		
		super(msg);
	}

}
