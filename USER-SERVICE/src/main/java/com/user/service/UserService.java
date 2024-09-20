package com.user.service;

import com.user.binding.LoginResponse;
import com.user.binding.UserBinding;

public interface UserService {

	public Boolean  userRegistration(UserBinding userBinding);
	
	public Boolean updateUser(UserBinding userBinding);
	
	public LoginResponse UserLogin(String username, String password);
	
	public UserBinding getUserById(Integer id);
	
	
}
