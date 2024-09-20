package com.user.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "NOTIFICATION-SERVICE")
public interface NotificationClient {

	@GetMapping("/notification/sendEmail")
	public String sednEmail();
}
