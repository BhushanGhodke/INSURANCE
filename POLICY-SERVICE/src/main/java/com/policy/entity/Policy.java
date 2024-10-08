package com.policy.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="policy")
public class Policy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer policyId;
	
	@NotEmpty(message = "firstname must not be null")
	@Size(min = 4, message = "firstname atleast 4 charcter required")
	private String firstname;

	@NotEmpty(message = "lastname must not be null")
	@Size(min = 4, message = "lastname atleast 4 charcter required")
	private String lastname;
	
	@NotEmpty(message = "mobile must not be null")
	@Size(min = 10,max=10, message = "mobile number 10 digit requied")
	private String mobile;
	
	@NotEmpty(message = "please select gender")
	private String gender;
	
	@NotEmpty(message = "please select coverage")
	private String coverage;
	
	@NotNull(message =  "please select date of birth")
	private LocalDate dob;
	
	@NotEmpty(message = "address must not be empty")
	private String address;
	
	@Email(message = "please enter valid email id")
	@NotEmpty(message = "email id must not be empty")
	private String email;
	
    @Min(value = 100000, message = "pincode must be of 6 deigit only")
    @Max(value =999999 , message = "pincode must be of 6 deigit only")
	private Integer pinCode;
	
	private Integer premiumAmount;
	
	private String paymentStatus;
	
	@CreationTimestamp
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	private String policyStatus;
	
	private Integer paymentId;
	
	private Integer userId;
	
	private Integer planId;
	
	public Integer getPolicyId() {
		return policyId;
	}
	public void setPolicyId(Integer policyId) {
		this.policyId = policyId;
	}

	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCoverage() {
		return coverage;
	}
	public void setCoverage(String coverage) {
		this.coverage = coverage;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getPinCode() {
		return pinCode;
	}
	public void setPinCode(Integer pinCode) {
		this.pinCode = pinCode;
	}
	public Integer getPremiumAmount() {
		return premiumAmount;
	}
	public void setPremiumAmount(Integer premiumAmount) {
		this.premiumAmount = premiumAmount;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public String getPolicyStatus() {
		return policyStatus;
	}
	public void setPolicyStatus(String policyStatus) {
		this.policyStatus = policyStatus;
	}
	public Integer getPlanId() {
		return planId;
	}
	public void setPlanId(Integer planId) {
		this.planId = planId;
	}
	public Integer getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}
	

}
