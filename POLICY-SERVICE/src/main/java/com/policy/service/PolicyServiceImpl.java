package com.policy.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

import com.policy.binding.*;
import com.policy.client.DocumentClient;
import com.policy.entity.PolicyType;
import com.policy.exception.PolicyExistsException;
import com.policy.repo.PolicyTypeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailParseException;
import org.springframework.stereotype.Service;

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

	@Autowired
	private DocumentClient documentClient;

	@Override

	public PolicyResponse createPolicy(PolicyRequest policyRequest) {

		Policy policy = policyRepository.findByEmailAndMobile(policyRequest.getEmail(), policyRequest.getMobile());

		System.out.println();

		if (policy == null) {

			Integer userId = policyRequest.getUserId();

			Policy newPolicy = new Policy();

			policyRequest.setPolicyStatus("CREATE");

			policyRequest.setPremiumAmount(2000);

			policyRequest.setEndDate(LocalDate.now().plusYears(1));

			policyRequest.setPaymentStatus("PENDING");

			BeanUtils.copyProperties(policyRequest, newPolicy);

			newPolicy.setUserId(userId);

			Policy save = policyRepository.save(newPolicy);

			PolicyResponse response = new PolicyResponse();

			response.setPolicyId(save.getPolicyId());

			response.setPolicyStatus(save.getPolicyStatus());

			response.setPaymentStatus(save.getPaymentStatus());

			response.setMessage("Policy created successfully Please Make Payment Now");

			return response;
		} else {
			throw new PolicyExistsException("Policy already exists");
		}

	}

	@Override
	public Boolean createPaymentForFreshPolicy(Integer policyId) {

		Boolean status = paymentClient.paymentStatusByPolicyId(policyId);

		Policy policy = policyRepository.findById(policyId).orElseThrow();

		System.out.println(status);
	

		if (status) {

			PaymentResponse paymentResponse = paymentClient.DoPaymentForPolicy(policyId);

			if (paymentResponse.getPaymentStatus().equals("SUCCESS")) {

				System.out.println("Payment successful");

				Email email = new Email();

				email.setMsg("Payment successful and Document Creatd Successfully");

				Boolean notify = notificationClient.sendEmail(email);

				System.out.println("Notification Email Sent :: Payment Successful");

				System.out.println(notify);

			}

			policy.setPaymentStatus(paymentResponse.getPaymentStatus());
			policy.setPolicyStatus("ACTIVE");

			policyRepository.save(policy);

			return true;
		}

		else {

			Email email = new Email();

			email.setMsg("Payment failed and document not created");

			Boolean emailStatus = notificationClient.sendEmail(email);

			System.out.println("Payment Failed Email Sent Successfully");

			return false;
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

		policyRepository.deleteById(policyId);
		
		Boolean status = paymentClient.deletePaymentDetailsByPolicyId(policyId);
		
		return "Policy Deleted";

	}
}
