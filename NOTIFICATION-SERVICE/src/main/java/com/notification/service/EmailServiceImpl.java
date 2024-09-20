package com.notification.service;

import org.springframework.stereotype.Service;

import com.notification.binding.Email;
@Service
public class EmailServiceImpl implements EmailService {

	@Override
	public Boolean sendEmail(Email email) {
		
		return true;
	}

}
