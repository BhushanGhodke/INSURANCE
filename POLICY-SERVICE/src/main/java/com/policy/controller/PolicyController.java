package com.policy.controller;

import java.util.List;

import com.policy.binding.PolicyInfoResponse;
import com.policy.entity.Policy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.policy.binding.PolicyRequest;
import com.policy.binding.PolicyResponse;
import com.policy.entity.PolicyType;
import com.policy.exception.PaymentFailedExcpetion;
import com.policy.repo.PolicyTypeRepository;
import com.policy.service.PolicyService;

@RestController
@RequestMapping("/policy")
@CrossOrigin
public class PolicyController {

	@Autowired
	private PolicyService policyService;
	
	@Autowired
	private PolicyTypeRepository policyTypeRepo;
	
	@PostMapping("/createPolicy")
	public ResponseEntity<PolicyResponse> createPolicy(@RequestBody PolicyRequest policyRequest){
		
		
		PolicyResponse response = policyService.createPolicy(policyRequest);
	
		return new ResponseEntity<PolicyResponse>(response, HttpStatus.OK);
	}
	
	@PostMapping("/payment/{policyId}")
	public ResponseEntity<String> paymentForPolicy(@PathVariable Integer policyId){
		
		Boolean status = policyService.createPaymentForFreshPolicy(policyId);
		
		if(status) {
			return new ResponseEntity<String>("Payment Done", HttpStatus.OK);
		}
		else {
			throw new PaymentFailedExcpetion("Payment Failed");
		}
	}
	
	@GetMapping("/policyTypes")
	public ResponseEntity<List<PolicyType>> getPolicyTypes(){
		
		List<PolicyType> policyTypes = policyTypeRepo.findAll();
		
		return new ResponseEntity<List<PolicyType>>(policyTypes, HttpStatus.OK);
	}

	@GetMapping("/getAllPolicy/{userId}")
	public ResponseEntity<List<PolicyInfoResponse>> getAllPolicyByUserId(@PathVariable Integer userId){

	List<PolicyInfoResponse>list=	policyService.getAllPolicies(userId);

	return new ResponseEntity<List<PolicyInfoResponse>>(list, HttpStatus.OK);
	}


	@GetMapping("/getPolicyById/{policyId}")
	public ResponseEntity<PolicyInfoResponse> getPolicyDetailsById(@PathVariable Integer policyId){

PolicyInfoResponse policyInfoResponse=policyService.FetchPolicyById(policyId);

return new ResponseEntity<>(policyInfoResponse, HttpStatus.OK);
	}
}
