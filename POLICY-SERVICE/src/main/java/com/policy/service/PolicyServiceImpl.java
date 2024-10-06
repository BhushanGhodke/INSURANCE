package com.policy.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.policy.binding.PaymentResponse;
import com.policy.binding.PolicyInfoForPayment;
import com.policy.binding.PolicyInfoResponse;
import com.policy.binding.PolicyRequest;
import com.policy.binding.PolicyResponse;
import com.policy.client.PaymentClient;
import com.policy.entity.Policy;
import com.policy.entity.PolicyType;
import com.policy.exception.PolicyExistsException;
import com.policy.exception.PolicyNotFoundException;
import com.policy.repo.PolicyRepository;
import com.policy.repo.PolicyTypeRepository;

@Service
public class PolicyServiceImpl implements PolicyService {

	@Autowired
	private PolicyRepository policyRepository;

	@Autowired
	private PaymentClient paymentClient;

	@Autowired
	private PolicyTypeRepository policyTypeRepository;

	@Override

	public PolicyResponse createPolicy(PolicyRequest policyRequest) {

		Policy policy = policyRepository.findByEmailAndMobile(policyRequest.getEmail(), policyRequest.getMobile());

		System.out.println();

		if (policy == null) {

			Policy newPolicy = new Policy();

			policyRequest.setPolicyStatus("DEACTIVE");

			policyRequest.setPremiumAmount(1);

			policyRequest.setEndDate(LocalDate.now().plusYears(1));

			BeanUtils.copyProperties(policyRequest, newPolicy);

			newPolicy.setUserId(policyRequest.getUserId());

			Policy savedPolicy = policyRepository.save(newPolicy);

			PolicyInfoForPayment policyInfoForPayment = new PolicyInfoForPayment();

			policyInfoForPayment.setEmail(savedPolicy.getEmail());

			policyInfoForPayment.setPremiumAmount(savedPolicy.getPremiumAmount());

			PaymentResponse paymentResponse = paymentClient.initiatePaymentForPolicy(policyInfoForPayment);

			savedPolicy.setPaymentStatus(paymentResponse.getPaymentStatus());

			savedPolicy.setPaymentId(paymentResponse.getPaymentId());
			 
			Policy save = policyRepository.save(savedPolicy);

			PolicyResponse response = new PolicyResponse();

			response.setPolicyId(save.getPolicyId());

			response.setPolicyStatus(save.getPolicyStatus());

			response.setRazorPayOrderId(paymentResponse.getRazorPayOrderId());
			
			response.setEmail(save.getEmail());
			
			response.setFirstname(save.getFirstname());
			
			response.setMobile(save.getMobile());
			
			response.setPremiumAmount(save.getPremiumAmount());
			
			response.setPaymentStatus(save.getPaymentStatus());

			response.setMessage("Policy created successfully Please Make Payment Now");

			return response;
		} else {
			throw new PolicyExistsException("Policy already exists");
		}

	}



	
	@Override
	public PolicyInfoResponse FetchPolicyById(Integer policyId) {

		Policy policy = policyRepository.findById(policyId)
				.orElseThrow(() -> new PolicyNotFoundException("Policy Not Found"));

		PolicyInfoResponse policyInfoResponse = new PolicyInfoResponse();
		Integer planId = policy.getPlanId();

		PolicyType policyType = policyTypeRepository.findByPlanId(planId);

		BeanUtils.copyProperties(policy, policyInfoResponse);

		policyInfoResponse.setPlanType(policyType.getPlans());
		return policyInfoResponse;
	}

	@Override
	public PolicyRequest getPolicyUserId(Integer userId) {

		Policy policy = policyRepository.findByUserId(userId);

		PolicyRequest request = new PolicyRequest();
		BeanUtils.copyProperties(policy, request);

		return request;
	}

	@Override
	public List<PolicyInfoResponse> getAllPolicies(Integer userId) {

		List<Policy> list = policyRepository.findAll().stream().filter((policy) -> policy.getUserId().equals(userId))
				.collect(Collectors.toList());

		List<PolicyInfoResponse> response = new ArrayList<>();

		list.forEach((policy) -> {

			Integer planId = policy.getPlanId();
			
			String planName = policyTypeRepository.findByPlanId(planId).getPlans();

			System.out.println(planName);

			PolicyInfoResponse policyInfoResponse = new PolicyInfoResponse();

			BeanUtils.copyProperties(policy, policyInfoResponse);
			policyInfoResponse.setPlanType(planName);
			response.add(policyInfoResponse);

		});
		return response;
	}

	@Override
	public Boolean updatePolicy(PolicyRequest policyRequest) {

		Policy policy = policyRepository.findById(policyRequest.getPolicyId())
				.orElseThrow(() -> new PolicyNotFoundException("Policy Not Found"));

		BeanUtils.copyProperties(policyRequest, policy);

		Policy updatedPolicy = policyRepository.save(policy);

		return updatedPolicy.getPolicyId() != null;
	}

	@Override
	public String DeletePolicyById(Integer policyId) {

		Policy policy = policyRepository.findById(policyId).orElseThrow();

		Boolean status = paymentClient.deletePaymentDetailsByPaymentId(policy.getPaymentId());

		policyRepository.deleteById(policyId);

		return "Policy Deleted";

	}
}
