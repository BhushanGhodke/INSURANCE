package com.document.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.document.entity.Policy;
import com.document.entity.PolicyType;

@FeignClient(name = "POLICY-SERVICE")
public interface PolicyClient {

	@GetMapping("/policy/getPolicyById/{policyId}")
	public Policy getPolicyById(@PathVariable Integer policyId);


}
