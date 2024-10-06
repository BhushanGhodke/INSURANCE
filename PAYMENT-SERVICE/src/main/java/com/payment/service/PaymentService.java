package com.payment.service;


import java.util.Map;

import com.payment.binding.PaymentResponse;
import com.payment.binding.PolicyInfoForPayment;
import com.payment.binding.RazorPayResponse;

public interface PaymentService {

    public PaymentResponse createPayment(PolicyInfoForPayment policyInfoForPayment)throws Exception;
    
    public PaymentResponse VerifyAndUpdatePaymentDetails (RazorPayResponse razorPayResponse);
 
    public Boolean PaymentStatusOfPolicyById(Integer policyId);
      
    public Boolean DeletePaymentDetailsByPaymentId(Integer paymentId);

}
