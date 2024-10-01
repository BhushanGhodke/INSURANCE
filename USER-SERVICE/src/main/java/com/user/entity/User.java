package com.user.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty
    @Size(min = 4, message = "Minimum 4 character required")
	private String username;
	
	@NotEmpty
	@Size(min=6, message = "minimum 6 character required")
	private String password;
	
	@NotEmpty
	@Email(message = "Invalid mail id")
	private String email;
	
	@NotEmpty
	@Size(min=10, max=10, message = "Mobile number must be 10 digit")
	private String mobile;
	
	@NotEmpty
	@Size(min = 4, message = "minimum 4 character requied")
	private String firstname;
	
	@NotEmpty
	@Size(min = 4, message = "minimum 4 character requied")
	private String lastname;
	
	@CreationTimestamp
	private LocalDate createdDate;
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
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



	
	
}
