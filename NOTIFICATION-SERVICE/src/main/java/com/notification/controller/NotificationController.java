package com.notification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notification.binding.Email;
import com.notification.exception.EmailFailureExcpetion;
import com.notification.service.EmailService;

@RestController
@RequestMapping("/notification")
public class NotificationController {

	@Autowired
	private EmailService emailService;
	
	@PostMapping("/sendEmail")
	public String sendEmail(@RequestBody Email email) {
		
		Boolean status = emailService.sendEmail(email);
	
		if(status) {
			return "SUCCESS";
		}
		else {
			throw new EmailFailureExcpetion("FAILED");
		}
	}
}
