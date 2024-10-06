package com.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment.binding.PaymentResponse;
import com.payment.binding.PolicyInfoForPayment;
import com.payment.binding.RazorPayResponse;
import com.payment.service.PaymentService;

@RestController
@RequestMapping("/payment")
@CrossOrigin
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PostMapping("/initiate-transaction")
	public ResponseEntity<PaymentResponse> initiatePaymentForPolicy(@RequestBody PolicyInfoForPayment policyInfoForPayment)
			throws Exception {
		PaymentResponse paymentResponse = paymentService.createPayment(policyInfoForPayment);
		return new ResponseEntity<>(paymentResponse, HttpStatus.OK);
	}

	@PostMapping("/handle-payment-callback")
	public ResponseEntity<PaymentResponse> handleCallBackMethod(@RequestBody RazorPayResponse razorPayResponse){
		
		System.out.println(razorPayResponse);
		
		PaymentResponse response = paymentService.VerifyAndUpdatePaymentDetails(razorPayResponse);
	
		return new ResponseEntity<PaymentResponse>(response, HttpStatus.OK);
	}
	
	@GetMapping("/paymentStatus/{policyId}")
	public ResponseEntity<Boolean> paymentStatusByPolicyId(@PathVariable Integer policyId) {

		Boolean status = paymentService.PaymentStatusOfPolicyById(policyId);

		return new ResponseEntity<Boolean>(status, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{paymentId}")
	public ResponseEntity<Boolean> deletePaymentDetailsByPolicyId(@PathVariable Integer paymentId) {
		Boolean status = paymentService.DeletePaymentDetailsByPaymentId(paymentId);

		return new ResponseEntity<Boolean>(status, HttpStatus.OK);
	}

}
