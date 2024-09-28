package com.payment.service;


import com.payment.binding.PaymentResponse;

public interface PaymentService {

    public PaymentResponse createPayment(Integer policyId);
    
    public Boolean PaymentStatusOfPolicyById(Integer policyId);
      
    public Boolean DeletePaymentDetailsByPolicyId(Integer policyId);

}
