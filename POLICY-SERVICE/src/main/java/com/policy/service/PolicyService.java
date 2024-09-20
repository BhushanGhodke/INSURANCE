package com.policy.service;

import com.policy.binding.PolicyInfoResponse;
import com.policy.binding.PolicyRequest;
import com.policy.binding.PolicyResponse;
import com.policy.entity.Policy;

import java.util.List;

public interface PolicyService {

	public PolicyResponse createPolicy(PolicyRequest policyRequest);
	
	public Boolean createPaymentForFreshPolicy(Integer policyId);
	
	public PolicyInfoResponse FetchPolicyById(Integer policyId);
	
	public Boolean updatePolicy(PolicyRequest policyRequest);
	
	public PolicyRequest getPolicyUserId(Integer userId);

	public List<PolicyInfoResponse> getAllPolicies(Integer userId);
}
