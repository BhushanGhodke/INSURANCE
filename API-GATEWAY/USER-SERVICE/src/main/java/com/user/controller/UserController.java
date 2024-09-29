package com.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.binding.LoginRequest;
import com.user.binding.LoginResponse;
import com.user.binding.UserBinding;
import com.user.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<String> UserRegistration(@RequestBody UserBinding userBinding) {

		Boolean status = userService.userRegistration(userBinding);

		if (status) {
			return new ResponseEntity<>("User registration successful", HttpStatus.OK);

		} else {
			return new ResponseEntity<>("Something Wen Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> LoginUser(@RequestBody LoginRequest login) {

		LoginResponse response = userService.UserLogin(login.getUsername(), login.getPassword());

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@PutMapping("/update")
	public ResponseEntity<String> updateUser(@RequestBody UserBinding userBinding) {

		Boolean status = userService.updateUser(userBinding);

		if (status) {
			return new ResponseEntity<>("User Updated", HttpStatus.OK);

		} else {
			return new ResponseEntity<>("Something Wen Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	

	@GetMapping("/getUserById/{id}")
	public ResponseEntity<UserBinding> getUserInfoById(@PathVariable Integer id) {

		UserBinding userBinding = userService.getUserById(id);
		
		return new ResponseEntity<>(userBinding, HttpStatus.OK);

	}
}
