package com.policy.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.policy.binding.PolicyInfoResponse;
import com.policy.entity.PolicyType;
import com.policy.repo.PolicyTypeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.policy.binding.Email;
import com.policy.binding.PolicyRequest;
import com.policy.binding.PolicyResponse;
import com.policy.client.NotificationClient;
import com.policy.client.PaymentClient;
import com.policy.entity.Policy;
import com.policy.exception.PaymentDoneException;
import com.policy.exception.PolicyNotFoundException;
import com.policy.repo.PolicyRepository;

@Service
public class PolicyServiceImpl implements PolicyService {

	@Autowired
	private PolicyRepository policyRepository;

	@Autowired
	private NotificationClient notificationClient;

	@Autowired
	private PaymentClient paymentClient;

	@Autowired
	private PolicyTypeRepository policyTypeRepository;

	@Override

	public PolicyResponse createPolicy(PolicyRequest policyRequest) {

		Policy policy = new Policy();

		policyRequest.setPolicyStatus("CREATE");

		policyRequest.setPremiumAmount(2000);
	
		policyRequest.setEndDate(LocalDate.now().plusYears(1));
		
		policyRequest.setPaymentStatus("PENDING");

		BeanUtils.copyProperties(policyRequest, policy);

		Policy save = policyRepository.save(policy);

		PolicyResponse response = new PolicyResponse();

		response.setPolicyId(save.getPolicyId());
		response.setPolicyStatus(save.getPolicyStatus());
		response.setPaymentStatus(save.getPaymentStatus());

		return response;

	}

	@Override
	public Boolean createPaymentForFreshPolicy(Integer policyId) {

		Policy policy = policyRepository.findById(policyId)
				.orElseThrow(() -> new PolicyNotFoundException("Policy with id " + policyId + " is not found"));

		String status = policy.getPaymentStatus();
		
		if(status.equals("PENDING")) {
			
			String paymentStatus = paymentClient.DoPaymentForPolicy();

			if (paymentStatus.equals("SUCCESS")) {

				Email email = new Email();

				policy.setPaymentStatus(paymentStatus);

				policyRepository.save(policy);

				String notify = notificationClient.sendEmail(email);

				System.out.println(notify);

				return true;
			}

			else {
				return false;
			}
			
		}else {
			throw new PaymentDoneException("Payment Already done");
		}
	}
	@Override
	public PolicyInfoResponse FetchPolicyById(Integer policyId) {

		Policy policy = policyRepository.findById(policyId)
				.orElseThrow(() -> new PolicyNotFoundException("Policy Not Found"));

		PolicyInfoResponse policyInfoResponse= new PolicyInfoResponse();
		Integer planId=policy.getPlanId();

		PolicyType policyType= policyTypeRepository.findByPlanId(planId);

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

	List< Policy> list= policyRepository.findAll().stream().filter((policy)->
			policy.getUserId().equals(userId)).collect(Collectors.toList());



	List<PolicyInfoResponse> response = new ArrayList<>();

	list.forEach((policy)->{

		Integer planId= policy.getPlanId();

		String planName= policyTypeRepository.findByPlanId(planId).getPlans();

		System.out.println(planName);

		PolicyInfoResponse policyInfoResponse = new PolicyInfoResponse();

		BeanUtils.copyProperties(policy,policyInfoResponse);
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
}
