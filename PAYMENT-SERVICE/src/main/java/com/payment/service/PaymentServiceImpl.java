package com.payment.service;

import java.util.Map;
import java.util.Random;

import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.razorpay.Order;
import com.payment.binding.PaymentResponse;
import com.payment.binding.PolicyInfoForPayment;
import com.payment.binding.RazorPayResponse;
import com.payment.entity.Payment;
import com.payment.repository.PaymentRepository;
import com.razorpay.RazorpayClient;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Value("${razorpay.key.id}")
	private String keyId;

	@Value("${razorpay.key.secret}")
	private String keySecret;

	private RazorpayClient client;

	@Override
	public PaymentResponse createPayment(PolicyInfoForPayment policyInfoForPayment) throws Exception {

		PaymentResponse paymentResponse = new PaymentResponse();

		JSONObject orderRequest = new JSONObject();
		orderRequest.put("amount", policyInfoForPayment.getPremiumAmount() * 100); // amount in paise
		orderRequest.put("currency", "INR");
		orderRequest.put("receipt", String.valueOf(policyInfoForPayment.getEmail()));
		System.out.println(keyId);
		System.out.println(keySecret);
		this.client = new RazorpayClient(keyId, keySecret);
		Order razorPayOrder = client.Orders.create(orderRequest);

		Payment payment = new Payment();

		payment.setRazorPayOrderId(razorPayOrder.get("id"));
		payment.setPaymentStatus(razorPayOrder.get("status"));

//		payment.setRazorPayOrderId(generateTransactionId());
//		payment.setPaymentStatus("create");

		Payment savedPayment = paymentRepository.save(payment);

		BeanUtils.copyProperties(savedPayment, paymentResponse);

		return paymentResponse;

	}

	@Override
	public PaymentResponse VerifyAndUpdatePaymentDetails(RazorPayResponse razorPayResponse) {

		String razorPayOrderId = razorPayResponse.getRazorpay_order_id();

		Payment payment = paymentRepository.findByRazorPayOrderId(razorPayOrderId);

		payment.setPaymentStatus("PAYMENT_COMPLETED");

		payment.setRazorpay_payment_id(razorPayResponse.getRazorpay_payment_id());

		Payment updatedPayment = paymentRepository.save(payment);

		PaymentResponse response = new PaymentResponse();

		BeanUtils.copyProperties(updatedPayment, response);

		return response;
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

		// Payment payment = paymentRepository.findByPolicyId(policyId);

		return false;
	}

	@Override
	public Boolean DeletePaymentDetailsByPaymentId(Integer paymentId) {

		paymentRepository.deleteById(paymentId);

		return true;
	}

}
