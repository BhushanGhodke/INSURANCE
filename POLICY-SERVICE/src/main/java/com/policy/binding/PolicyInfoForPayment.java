package com.policy.binding;

public class PolicyInfoForPayment {



	private String email;

	private Integer premiumAmount;

	private String policyStatus;
	
	private String razorPayOrderId;

	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getPremiumAmount() {
		return premiumAmount;
	}

	public void setPremiumAmount(Integer premiumAmount) {
		this.premiumAmount = premiumAmount;
	}

	public String getPolicyStatus() {
		return policyStatus;
	}

	public void setPolicyStatus(String policyStatus) {
		this.policyStatus = policyStatus;
	}

	public String getRazorPayOrderId() {
		return razorPayOrderId;
	}

	public void setRazorPayOrderId(String razorPayOrderId) {
		this.razorPayOrderId = razorPayOrderId;
	}
	
	
	
}
