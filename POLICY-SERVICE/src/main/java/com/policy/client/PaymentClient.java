package com.policy.client;

import com.policy.binding.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PAYMENT-SERVICE")
public interface PaymentClient {

	@GetMapping("/payment/transaction/{policyId}")
	public PaymentResponse DoPaymentForPolicy(@PathVariable Integer policyId);

	@GetMapping("/payment/paymentStatus/{policyId}")
	public Boolean paymentStatusByPolicyId(@PathVariable Integer policyId);
	
	@DeleteMapping("/payment/delete/{policyId}")
	public Boolean deletePaymentDetailsByPolicyId(@PathVariable Integer policyId);
}
