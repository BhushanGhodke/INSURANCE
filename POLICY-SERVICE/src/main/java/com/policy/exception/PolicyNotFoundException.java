package com.policy.exception;

public class PolicyNotFoundException extends RuntimeException {

	public PolicyNotFoundException() {
	
	}
	
	public PolicyNotFoundException(String msg) {
	
		super(msg);
	}
}
