package com.policy.exception;

public class PaymentFailedExcpetion extends RuntimeException {

	public PaymentFailedExcpetion() {
	
	}
	
	public PaymentFailedExcpetion(String msg) {
		
		super(msg);
	}
}
