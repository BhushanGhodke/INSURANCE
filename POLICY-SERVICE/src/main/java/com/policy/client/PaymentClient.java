package com.policy.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.policy.binding.PaymentResponse;
import com.policy.binding.PolicyInfoForPayment;

@FeignClient(name = "PAYMENT-SERVICE")
public interface PaymentClient {

	@PostMapping("/payment/initiate-transaction")
	public PaymentResponse initiatePaymentForPolicy(@RequestBody PolicyInfoForPayment policyInfoForPayment);

	@GetMapping("/payment/paymentStatus/{policyId}")
	public Boolean paymentStatusByPolicyId(@PathVariable Integer policyId);
	
	@DeleteMapping("/payment/delete/{paymentId}")
	public Boolean deletePaymentDetailsByPaymentId(@PathVariable Integer paymentId);

	
}
