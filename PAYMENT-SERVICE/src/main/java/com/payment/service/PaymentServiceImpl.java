package com.payment.service;

import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.binding.PaymentResponse;
import com.payment.entity.Payment;
import com.payment.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public PaymentResponse createPayment(Integer policyId) {

		Thread t = new Thread();
		PaymentResponse paymentResponse = new PaymentResponse();
		try {
			
			String transactionId = generateTransactionId();
			Payment payment = new Payment();
			payment.setTransactionId(transactionId);
			payment.setPolicyId(policyId);
			payment.setPaymentStatus("SUCCESS");
			Payment savedPayment = paymentRepository.save(payment);
			
			BeanUtils.copyProperties(savedPayment, paymentResponse);
			Thread.sleep(3000);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return paymentResponse;

	}

	private String generateTransactionId() {

		String a = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i <= 15; i++) {

			Random random = new Random();
			Integer randomNUmber = random.nextInt(a.length() - 1);
			sb.append(a.charAt(randomNUmber));
		}

		return sb.toString();

	}

	@Override
	public Boolean PaymentStatusOfPolicyById(Integer policyId) {

		Payment payment = paymentRepository.findByPolicyId(policyId);

		return payment == null;
	}
	
	
	@Override
	public Boolean DeletePaymentDetailsByPolicyId(Integer policyId) {
		
		Payment payment = paymentRepository.findByPolicyId(policyId);
		
		if(payment!=null) {
			
			paymentRepository.deleteById(payment.getPaymentId());
			return true;
		}
		return false;
	}
}
