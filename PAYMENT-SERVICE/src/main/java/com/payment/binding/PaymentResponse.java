package com.payment.binding;

import java.sql.Date;

public class PaymentResponse {

	  private Integer paymentId;
	    private String razorPayOrderId;
	    private String paymentStatus;
	    private Date paymentDate;
	    private String razorpay_payment_id;
	

	    
	    
    public String getRazorpay_payment_id() {
			return razorpay_payment_id;
		}

		public void setRazorpay_payment_id(String razorpay_payment_id) {
			this.razorpay_payment_id = razorpay_payment_id;
		}

	public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

  

	public String getRazorPayOrderId() {
		return razorPayOrderId;
	}

	public void setRazorPayOrderId(String razorPayOrderId) {
		this.razorPayOrderId = razorPayOrderId;
	}

  
}
