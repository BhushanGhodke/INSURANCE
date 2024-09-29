package com.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByEmailAndUsername(String email, String username);
	
	public User findByUsername(String username);
	
	
}