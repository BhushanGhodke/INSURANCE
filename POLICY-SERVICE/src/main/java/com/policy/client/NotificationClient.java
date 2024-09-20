package com.policy.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.policy.binding.Email;

@FeignClient(name = "NOTIFICATION-SERVICE")
public interface NotificationClient {

	@PostMapping("/notification/sendEmail")
	public String sendEmail(Email email);
}
