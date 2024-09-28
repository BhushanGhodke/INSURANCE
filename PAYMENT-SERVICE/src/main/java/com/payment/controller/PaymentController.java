package com.payment.controller;

import com.payment.binding.PaymentResponse;
import com.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@GetMapping("/transaction/{policyId}")
	public ResponseEntity<PaymentResponse> DoPaymentTransaction(@PathVariable Integer policyId) {
          PaymentResponse paymentResponse= paymentService.createPayment(policyId);
		return new ResponseEntity<>(paymentResponse, HttpStatus.OK);
	}
	
	@GetMapping("/paymentStatus/{policyId}")
	public ResponseEntity<Boolean> paymentStatusByPolicyId(@PathVariable Integer policyId){
		
		Boolean status = paymentService.PaymentStatusOfPolicyById(policyId);

		return new ResponseEntity<Boolean>(status, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{policyId}")
	public ResponseEntity<Boolean> deletePaymentDetailsByPolicyId(@PathVariable Integer policyId){
		Boolean status = paymentService.DeletePaymentDetailsByPolicyId(policyId);
	
		return new ResponseEntity<Boolean>(status, HttpStatus.OK);
	}
}
