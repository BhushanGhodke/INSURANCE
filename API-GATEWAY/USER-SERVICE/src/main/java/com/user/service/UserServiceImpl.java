package com.user.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.binding.LoginResponse;
import com.user.binding.UserBinding;
import com.user.client.NotificationClient;
import com.user.entity.User;
import com.user.exception.IncorrectCredentialException;
import com.user.exception.UserAlredyRegisterExcpetion;
import com.user.exception.UserNotFoundException;
import com.user.repo.UserRepository;
import com.user.utils.EmailUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private EmailUtils emailUtils;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private NotificationClient notificationClient;

	@Override
	public Boolean userRegistration(UserBinding userBinding) {

		User existingUser = userRepository.findByEmailAndUsername(userBinding.getEmail(),userBinding.getUsername());

		User user = new User();

		if (existingUser != null) {

			throw new UserAlredyRegisterExcpetion("User Already Register With Us! ");
		}

		else {

			BeanUtils.copyProperties(userBinding, user);

			User savedUser = userRepository.save(user);

		//	String sendEmail = notificationClient.sednEmail();

		//	System.out.println(sendEmail);

			return savedUser.getId() != null;
		}

	}

	@Override
	public Boolean updateUser(UserBinding userBinding) {

		User user = userRepository.findById(userBinding.getId())
				.orElseThrow(() -> new UserNotFoundException("User Does Not Found"));

		BeanUtils.copyProperties(userBinding, user);

		User savedUser = userRepository.save(user);

		return savedUser.getId() != null;
	}

	@Override
	public LoginResponse UserLogin(String username, String password) {

		User user = userRepository.findByUsername(username);

		if (user == null) {

			throw new IncorrectCredentialException("Invalid Credential !!");
		} else {
			boolean status = user.getPassword().equals(password);

			LoginResponse response = new LoginResponse();
			if (status) {

				response.setId(user.getId());
				response.setStatus(status);
				response.setFirstname(user.getFirstname());
				response.setLastname(user.getLastname());
				return response;

			} else {
				throw new IncorrectCredentialException("Invalid Credential !!");

			}

		}
	}
	
	
	@Override
	public UserBinding getUserById(Integer id) {
	
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User Does Not Found"));

		UserBinding binding = new UserBinding();
		
		BeanUtils.copyProperties(user, binding);
		
		return binding;
	}
}
