package com.policy.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import com.policy.binding.PolicyInfoResponse;
import com.policy.binding.PolicyRequest;
import com.policy.binding.PolicyResponse;

public interface PolicyService {

	public PolicyResponse createPolicy(PolicyRequest policyRequest);

	
	
	public PolicyInfoResponse FetchPolicyById(Integer policyId);
	
	public Boolean updatePolicy(PolicyRequest policyRequest);
	
	public PolicyRequest getPolicyUserId(Integer userId);

	public List<PolicyInfoResponse> getAllPolicies(Integer userId);

	public String DeletePolicyById(Integer policyId);
}
