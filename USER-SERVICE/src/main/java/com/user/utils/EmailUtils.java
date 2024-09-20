package com.user.utils;

import org.springframework.stereotype.Component;

@Component
public class EmailUtils {

	public Boolean sendEmail() {
		
		System.out.println("Email Sent SuccessFully");
		return true;
	}
}
